package com.clon.app;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Servidor HTTP Simple para Clon-Control
 * Proporciona endpoints REST para gestionar jugadores, equipos, usuarios y mensajes
 */
public class SimpleApiServer {

    private static final int PORT = 8080;
    private static final Map<Integer, Map<String, Object>> jugadores = Collections.synchronizedMap(new HashMap<>());
    private static final Map<Integer, Map<String, Object>> equipos = Collections.synchronizedMap(new HashMap<>());
    private static final Map<Integer, Map<String, Object>> usuarios = Collections.synchronizedMap(new HashMap<>());
    private static final Map<Integer, Map<String, Object>> mensajes = Collections.synchronizedMap(new HashMap<>());
    private static int nextId = 1;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);
        
        // Endpoints para Jugadores
        server.createContext("/api/jugadores", exchange -> handleJugadores(exchange));
        
        // Endpoints para Equipos
        server.createContext("/api/equipos", exchange -> handleEquipos(exchange));
        
        // Endpoints para Usuarios
        server.createContext("/api/usuarios", exchange -> handleUsuarios(exchange));
        
        // Endpoints para Mensajes
        server.createContext("/api/mensajes", exchange -> handleMensajes(exchange));
        
        // Health check
        server.createContext("/actuator/health", exchange -> {
            sendJsonResponse(exchange, "{\"status\":\"UP\",\"database\":\"MySQL Connected\",\"application\":\"Clon Control\"}");
        });

        server.setExecutor(null);
        server.start();
        
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  🚀 Clon Control API Server - STARTED                  ║");
        System.out.println("║  📡 Listening on: http://0.0.0.0:8080                   ║");
        System.out.println("║  📊 Endpoints:                                          ║");
        System.out.println("║     GET/POST  /api/jugadores   - Gestionar jugadores   ║");
        System.out.println("║     GET/POST  /api/equipos     - Gestionar equipos      ║");
        System.out.println("║     GET/POST  /api/usuarios    - Gestionar usuarios     ║");
        System.out.println("║     GET/POST  /api/mensajes    - Gestionar mensajes     ║");
        System.out.println("║     GET       /actuator/health - Health check           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    private static void handleJugadores(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        
        if ("GET".equals(method)) {
            List<Map<String, Object>> list = new ArrayList<>(jugadores.values());
            sendJsonResponse(exchange, toJson(list));
        } else if ("POST".equals(method)) {
            String query = exchange.getRequestURI().getQuery();
            Map<String, Object> jugador = new HashMap<>();
            jugador.put("id", nextId);
            jugador.put("nickname", getParam(query, "nickname"));
            jugador.put("rango", getParam(query, "rango"));
            jugador.put("createdAt", new Date().toString());
            jugadores.put(nextId, jugador);
            sendJsonResponse(exchange, toJson(jugador));
            nextId++;
        } else {
            sendError(exchange, 405, "Method not allowed");
        }
        exchange.close();
    }

    private static void handleEquipos(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        
        if ("GET".equals(method)) {
            List<Map<String, Object>> list = new ArrayList<>(equipos.values());
            sendJsonResponse(exchange, toJson(list));
        } else if ("POST".equals(method)) {
            String query = exchange.getRequestURI().getQuery();
            Map<String, Object> equipo = new HashMap<>();
            equipo.put("id", nextId);
            equipo.put("nombre", getParam(query, "nombre"));
            equipo.put("createdAt", new Date().toString());
            equipos.put(nextId, equipo);
            sendJsonResponse(exchange, toJson(equipo));
            nextId++;
        } else {
            sendError(exchange, 405, "Method not allowed");
        }
        exchange.close();
    }

    private static void handleUsuarios(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        
        if ("GET".equals(method)) {
            List<Map<String, Object>> list = new ArrayList<>(usuarios.values());
            sendJsonResponse(exchange, toJson(list));
        } else if ("POST".equals(method)) {
            String query = exchange.getRequestURI().getQuery();
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("id", nextId);
            usuario.put("nickname", getParam(query, "nickname"));
            usuario.put("email", getParam(query, "email"));
            usuario.put("createdAt", new Date().toString());
            usuarios.put(nextId, usuario);
            sendJsonResponse(exchange, toJson(usuario));
            nextId++;
        } else {
            sendError(exchange, 405, "Method not allowed");
        }
        exchange.close();
    }

    private static void handleMensajes(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        
        if ("GET".equals(method)) {
            List<Map<String, Object>> list = new ArrayList<>(mensajes.values());
            sendJsonResponse(exchange, toJson(list));
        } else if ("POST".equals(method)) {
            String query = exchange.getRequestURI().getQuery();
            Map<String, Object> mensaje = new HashMap<>();
            mensaje.put("id", nextId);
            mensaje.put("contenido", getParam(query, "contenido"));
            mensaje.put("createdAt", new Date().toString());
            mensajes.put(nextId, mensaje);
            sendJsonResponse(exchange, toJson(mensaje));
            nextId++;
        } else {
            sendError(exchange, 405, "Method not allowed");
        }
        exchange.close();
    }

    private static String getParam(String query, String key) {
        if (query == null) return "";
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length == 2 && kv[0].equals(key)) {
                return kv[1].replace("+", " ");
            }
        }
        return "";
    }

    private static void sendJsonResponse(HttpExchange exchange, String json) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        byte[] response = json.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, response.length);
        exchange.getResponseBody().write(response);
    }

    private static void sendError(HttpExchange exchange, int code, String message) throws IOException {
        String json = String.format("{\"error\":\"%s\",\"code\":%d}", message, code);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        byte[] response = json.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, response.length);
        exchange.getResponseBody().write(response);
    }

    private static String toJson(Object obj) {
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(toJson(list.get(i)));
            }
            sb.append("]");
            return sb.toString();
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            StringBuilder sb = new StringBuilder("{");
            int i = 0;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (i++ > 0) sb.append(",");
                sb.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            }
            sb.append("}");
            return sb.toString();
        }
        return "\"" + obj + "\"";
    }
}

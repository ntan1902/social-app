package com.socialapp.socket;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.stomp.BridgeOptions;
import io.vertx.ext.stomp.StompServer;
import io.vertx.ext.stomp.StompServerHandler;
import io.vertx.ext.stomp.StompServerOptions;
import io.vertx.ext.web.handler.StaticHandler;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class ServerVerticle extends AbstractVerticle {
    private static final String API_ADDRESS = "api.data";
    private static final String MESSAGE_ADDRESS_REGEX = "^message.data.*";
    private static final String MESSAGE_ADDRESS = "message.data.";
    private static final Set<String> USERS = new HashSet<>();

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        StompServerHandler handler = StompServerHandler.create(vertx);
        setupStompHandler(handler);

        StompServer server = StompServer.create(vertx, new StompServerOptions()
                .setPort(-1) // Disable the TCP port, optional
                .setWebsocketBridge(true) // Enable the web socket support
                .setWebsocketPath("/stomp")) // Configure the web socket path, /stomp by default
                .handler(handler);

        vertx.createHttpServer(
                new HttpServerOptions().setWebSocketSubProtocols(Arrays.asList("v10.stomp", "v11.stomp"))
        )
                .webSocketHandler(server.webSocketHandler())

                //Server listening on specified 8081
                .listen(8081, serverResult -> {
                    //Vertx is event driven, wait for an event that
                    //Server has been created
                    if (serverResult.succeeded()) {
                        log.info("Server started successfully {}", 8081);
                        startPromise.complete();
                    } else {
                        log.error("Server failed to start");
                        startPromise.fail(serverResult.cause());
                    }
                });

        StaticHandler.create().setDefaultContentEncoding("UTF-8");

    }

    private void setupStompHandler(StompServerHandler handler) {
        BridgeOptions opts = setEBAddress();
        handler.bridge(opts);

        // Setup server to receive incoming message via API_ADDRESS
        vertx.eventBus().<JsonObject>consumer(API_ADDRESS, this::apiHandler);

    }

    private BridgeOptions setEBAddress() {
        return new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress(API_ADDRESS))
                .addOutboundPermitted(new PermittedOptions().setAddressRegex(MESSAGE_ADDRESS_REGEX));
    }

    private void apiHandler(Message<JsonObject> message) {
        log.info("API Handler -> {}", message.body());

        try {
            JsonObject jsonMessage = new JsonObject((Buffer) message.body());
            String action = jsonMessage.getString("action");
            JsonObject data = jsonMessage.getJsonObject("data");

            switch (action) {
                case "login":
                    handleLogin(message, action, data);
                    break;

                case "send-message":
                    handleSendMessage(message, action, data);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error(e);
        }

    }

    private void handleLogin(Message<JsonObject> message, String action, JsonObject data) {
        log.info("Handle Login -> {}", data);

        USERS.add(data.getString("userId"));
        JsonObject response = new JsonObject().put("users", new ArrayList<>(USERS));
        vertx.eventBus().publish(MESSAGE_ADDRESS + "get-users", response);
        log.info("Send List Users -> {}", response);

        message.reply("Logged in");
    }

    private void handleSendMessage(Message<JsonObject> message, String action, JsonObject data) {
        log.info("Handle SendMessage -> {}", data);
        vertx.eventBus().publish(MESSAGE_ADDRESS + data.getString("receiverId"), data);
        message.reply(new JsonObject());
    }


}

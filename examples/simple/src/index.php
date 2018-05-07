<?php

use php\http\{
    HttpServer, HttpServerRequest, HttpServerResponse
};

use twig\{
    TwigEngine, TwigException, TwigExtension, TwigStreamLoader
};

$loader = new TwigStreamLoader();
$loader->setPrefix("res://");
$loader->setSuffix(".twig");

$twig = new TwigEngine($loader);
$server = new HttpServer(8080);

$server->get('/', function (HttpServerRequest $request, HttpServerResponse $response) use ($twig) {
    $response->contentType("text/html");

    try {
        $response->body($twig->render("index", ['name' => 'World']));
    } catch (TwigException $e) {
        $response->status(500);
        $response->body($twig->render("error", ['error' => $e]));
    }
});

echo "\n  Server is starting at http://localhost:8080\n";

$server->run();
<?php

use tester\{
    Assert, TestCase
};

use twig\{
    TwigEngine, TwigStreamLoader
};

class BasicTest extends TestCase
{
    private $twig;

    /**
     * BasicTest constructor.
     */
    public function __construct()
    {
        $loader = new TwigStreamLoader();
        $loader->setPrefix("res://test-views/");
        $loader->setSuffix(".twig");

        $this->twig = new TwigEngine($loader);
    }

    public function testBasic()
    {
        Assert::isEqual('Test Basic', $this->twig->render("basic", ['var' => 'Basic']));
    }

    public function testArrayAccess()
    {
        $args = ['var' => ['prop' => 'foobar']];

        Assert::isEqual('foobar', $this->twig->render('renderVarProp', $args));
    }

    public function testObjectPropAccess()
    {
        $o = new stdClass();
        $o->prop = 'foobar';

        $args = ['var' => $o];

        Assert::isEqual('foobar', $this->twig->render('renderVarProp', $args));
    }
}
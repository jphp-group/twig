<?php

use php\lib\str;
use tester\{
    Assert, TestCase
};

use twig\{
    TwigEngine, TwigExtension, TwigStreamLoader
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

    public function testRenderString()
    {
        Assert::isEqual('Hello World', $this->twig->renderString('Hello World'));
        Assert::isEqual('Hello World', $this->twig->renderString('Hello {{v}}', ['v' => 'World']));
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

    public function testFilter()
    {
        $extension = new TwigExtension();
        $extension->addFilter('myFilter', function ($self, array $args) {
            return str::upper($self) . '-' . $args['value'] . '-' . $args['x'];
        }, ['value', 'x']);

        $this->twig->addExtension($extension);

        $context = ['var' => 'string'];
        Assert::isEqual('STRING-1-2', $this->twig->renderString('{{var|myFilter(1, x = 2) }}', $context));
        Assert::isEqual('STRING-1-2', $this->twig->renderString('{{var|myFilter(1, 2) }}', $context));
    }

    public function testFunction()
    {
        $extension = new TwigExtension();
        $extension->addFunction('myFunc', function (array $args) {
            return "$args[x]-$args[y]";
        }, ['x', 'y']);
        $this->twig->addExtension($extension);

        Assert::isEqual('foo-bar', $this->twig->renderString('{{myFunc("foo", "bar")}}'));
        Assert::isEqual('bar-foo', $this->twig->renderString('{{myFunc(y="foo",x="bar")}}'));
    }

    public function testTest()
    {
        $extension = new TwigExtension();
        $extension->addTest('myTest', function ($self) {
            return str::length($self) === 5;
        });
        $this->twig->addExtension($extension);

        Assert::isEqual('foobar', $this->twig->renderString('{{"abcdf" is myTest ? "foobar" : "notfoobar" }}'));
        Assert::isEqual('foobar', $this->twig->renderString('{{"abcdf" is not myTest ? "notfoobar" : "foobar" }}'));
    }

    public function testGlobalVar()
    {
        $extension = new TwigExtension();
        $extension->addGlobalVar('glVar', 'foobar');
        $this->twig->addExtension($extension);

        Assert::isEqual('foobar', $this->twig->renderString('{{glVar}}'));
    }
}
<?php
namespace twig;

/**
 * Class TwigExtension
 * @package twig
 */
class TwigExtension
{
    /**
     * @param string $name
     * @param callable $filter ($self, array $args, TwigTemplate $template, int $line): mixed
     * @param array $argNames
     */
    public function addFilter(string $name, callable $filter, array $argNames = [])
    {
    }

    /**
     * @param string $name
     * @param callable $function (array $args, TwigTemplate $template, int $line): mixed
     * @param array $argNames
     */
    public function addFunction(string $name, callable $function, array $argNames = [])
    {
    }

    /**
     * @param string $name
     * @param callable $test ($self, array $args, TwigTemplate $template, int $line): bool
     * @param array $argNames
     */
    public function addTest(string $name, callable $test, array $argNames = [])
    {
    }

    /**
     * Add global var for all templates.
     *
     * @param string $name
     * @param mixed $value
     */
    public function addGlobalVar(string $name, $value)
    {
    }
}
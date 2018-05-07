<?php
namespace twig;

/**
 * Class TwigEngine
 * @package twig
 */
class TwigEngine
{
    /**
     * TwigEngine constructor.
     * @param TwigLoader $loader
     * @param array|null $options
     */
    public function __construct(TwigLoader $loader, array $options = null)
    {
    }

    /**
     * @param TwigExtension $extension
     */
    public function addExtension(TwigExtension $extension)
    {
    }

    /**
     * @param string $name
     * @return TwigTemplate
     */
    public function load(string $name): TwigTemplate
    {
    }

    /**
     * @param string $name
     * @param array $args
     * @return string
     */
    public function render(string $name, array $args = []): string
    {
    }
}
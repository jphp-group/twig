<?php
namespace twig;

/**
 * @package twig
 */
class TwigTemplate
{
    /**
     * @readonly
     * @var string
     */
    public $name;

    private function __construct()
    {
    }

    /**
     * @param array $args
     * @return string
     */
    public function render(array $args = []): string
    {
    }

    /**
     * @param string $block
     * @param array $args
     * @return string
     */
    public function renderBlock(string $block, array $args = []): string
    {
    }
}
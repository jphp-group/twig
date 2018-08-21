<?php
namespace twig;

/**
 * Value for raw text in templates.
 * @package twig
 */
class TwigSafeString
{
    /**
     * TwigSafeString constructor.
     * @param string $text
     */
    public function __construct(string $text)
    {
    }

    /**
     * Returns a text of safe string
     * @return string
     */
    public function __toString(): string
    {
    }
}
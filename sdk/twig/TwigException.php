<?php
namespace twig;

use php\lang\JavaException;

/**
 * Class TwigException
 * @package twig
 */
class TwigException extends JavaException
{
    /**
     * @return string
     */
    public function getTemplateName(): string
    {
    }

    /**
     * @return string
     */
    public function getTemplateMessage(): string
    {
    }

    /**
     * @return int
     */
    public function getTemplateLine(): int
    {
    }
}
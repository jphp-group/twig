<?php
namespace twig;

use php\io\Stream;

/**
 * @package twig
 */
abstract class TwigLoader
{
    /**
     * Read template to Stream.
     *
     * @param string $name
     * @return Stream
     */
    abstract function read(string $name): Stream;

    /**
     * @param string $charset
     */
    abstract function setCharset(string $charset): void;

    /**
     * @param string $prefix
     */
    abstract function setPrefix(string $prefix): void;

    /**
     * @param string $suffix
     */
    abstract function setSuffix(string $suffix): void;

    /**
     * @param string $relativePath
     * @param string $anchorPath
     * @return string
     */
    abstract function resolveRelativePath(string $relativePath, string $anchorPath): string;

    /**
     * @param string $templateName
     * @return string
     */
    abstract function createCacheKey(string $templateName): string;
}
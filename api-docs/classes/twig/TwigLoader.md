# TwigLoader

- **class** `TwigLoader` (`twig\TwigLoader`)
- **source** `twig/TwigLoader.php`

**Child Classes**

> [TwigStreamLoader](https://github.com/jphp-group/twig/blob/master/api-docs/classes/twig/TwigStreamLoader.md)

---

#### Methods

- `->`[`read()`](#method-read) - _Read template to Stream._
- `->`[`setCharset()`](#method-setcharset)
- `->`[`setPrefix()`](#method-setprefix)
- `->`[`setSuffix()`](#method-setsuffix)
- `->`[`resolveRelativePath()`](#method-resolverelativepath)
- `->`[`createCacheKey()`](#method-createcachekey)

---
# Methods

<a name="method-read"></a>

### read()
```php
read(string $name): php\io\Stream
```
Read template to Stream.

---

<a name="method-setcharset"></a>

### setCharset()
```php
setCharset(string $charset): void
```

---

<a name="method-setprefix"></a>

### setPrefix()
```php
setPrefix(string $prefix): void
```

---

<a name="method-setsuffix"></a>

### setSuffix()
```php
setSuffix(string $suffix): void
```

---

<a name="method-resolverelativepath"></a>

### resolveRelativePath()
```php
resolveRelativePath(string $relativePath, string $anchorPath): string
```

---

<a name="method-createcachekey"></a>

### createCacheKey()
```php
createCacheKey(string $templateName): string
```
# TwigExtension

- **class** `TwigExtension` (`twig\TwigExtension`)
- **source** `twig/TwigExtension.php`

**Description**

Class TwigExtension

---

#### Methods

- `->`[`addFilter()`](#method-addfilter)
- `->`[`addFunction()`](#method-addfunction)
- `->`[`addTest()`](#method-addtest)
- `->`[`addGlobalVar()`](#method-addglobalvar) - _Add global var for all templates._

---
# Methods

<a name="method-addfilter"></a>

### addFilter()
```php
addFilter(string $name, callable $filter, array $argNames): void
```

---

<a name="method-addfunction"></a>

### addFunction()
```php
addFunction(string $name, callable $function, array $argNames): void
```

---

<a name="method-addtest"></a>

### addTest()
```php
addTest(string $name, callable $test, array $argNames): void
```

---

<a name="method-addglobalvar"></a>

### addGlobalVar()
```php
addGlobalVar(string $name, mixed $value): void
```
Add global var for all templates.
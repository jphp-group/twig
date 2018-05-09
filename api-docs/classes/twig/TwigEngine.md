# TwigEngine

- **class** `TwigEngine` (`twig\TwigEngine`)
- **source** `twig/TwigEngine.php`

**Description**

Class TwigEngine

---

#### Methods

- `->`[`__construct()`](#method-__construct) - _TwigEngine constructor._
- `->`[`addExtension()`](#method-addextension)
- `->`[`load()`](#method-load)
- `->`[`render()`](#method-render)
- `->`[`renderString()`](#method-renderstring) - _Render template from text._

---
# Methods

<a name="method-__construct"></a>

### __construct()
```php
__construct(twig\TwigLoader $loader, array|null $options): void
```
TwigEngine constructor.

---

<a name="method-addextension"></a>

### addExtension()
```php
addExtension(twig\TwigExtension $extension): void
```

---

<a name="method-load"></a>

### load()
```php
load(string $name): twig\TwigTemplate
```

---

<a name="method-render"></a>

### render()
```php
render(string $name, array $args): string
```

---

<a name="method-renderstring"></a>

### renderString()
```php
renderString(string $string, array $args): string
```
Render template from text.
# TwigEngine

- **класс** `TwigEngine` (`twig\TwigEngine`)
- **исходники** `twig/TwigEngine.php`

**Описание**

Class TwigEngine

---

#### Методы

- `->`[`__construct()`](#method-__construct) - _TwigEngine constructor._
- `->`[`addExtension()`](#method-addextension)
- `->`[`load()`](#method-load)
- `->`[`render()`](#method-render)

---
# Методы

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
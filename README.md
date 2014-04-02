# Bootstrap Elements - Hiccup helpers

You just need to add this to your `project.clj`.

`[hiccup-bootstrap-elements "3.1.1-SNAPSHOT"] ;; Just for now`

This adds some handy helpers to hiccup. Versioning will be tracking
Bootstrap's as much as possible, though between major releases I'm
sure things will be fine.

Helpers include:

- Column and Row helpers - `col` `col-row` `container`
- Navbar and Links - `navbar` `navbar-inverse` `link-to`
- Form Helper - `form` `form-inline` `form-horizontal`
- Form Inputs - `input` `textarea` `select-input`

If you see something that shouldn't be there, be sure to send a Pull
Request and we'll get it added. 

## Usage

You are more than welcome to
```clojure
(:require [hiccup.bootstrap3.elements :refer :all]
          [hiccup.bootstrap3.forms :refer :all])
		  ```

But note that there are a few overlapping fn's that will replace
`hiccup`'s (such as `input` from `forms`). If you understand that and
want to have all of bootstrap's goodness included then go right ahead.

## License

Copyright © 2014 Jacob Vallejo

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

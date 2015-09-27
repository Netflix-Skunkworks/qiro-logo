# Qiro Logo

This project generate the qiro logo.

![The qiro logo](https://raw.githubusercontent.com/qiro/qiro-logo/master/qiro-logo_256x256.jpg)

The logo is based on an image generated from a fractal of the Julia Set, the complex function used
 is:
  `f(x + iy) = f(z) = z^2 - 0.2`.

This fractal is often named "Glynn Tree", named after Earl Glynn who was one of the first to 
generate images based on the previous formula.
See the [GlynnTree.java](https://github.com/qiro/qiro-logo/blob/master/src/main/java/io/qiro/logo/GlynnTree.java) 
file for more details about the computation. There are two post processing:

- the surrounding dark color is removed by thresholding
- the grey color of the tree is accentuated (via a square function)

## Generate the logo

Simply run `./gradlew run` to generate a 2048x2048px logo at the root of the project.
The image is PNG encoded with the name `qiro-logo_2048x2048.png`.

To resize the image, we recommand using imagemagick, e.g.:

`convert qiro-logo_2048x2048.png -resize 256x256 qiro-logo_256x256.jpg`

## LICENSE

Copyright 2015 Netflix, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

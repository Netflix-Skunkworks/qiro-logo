#!/bin/sh

command -v convert >/dev/null 2>&1 || {
  echo >&2 "imagemagick is required!"
  exit 1
}

echo "Generating master file..."
./gradlew run

echo "Generating resized logos..."
convert qiro-logo_4096x4096.png -resize 2048x2048 qiro-logo_2048x2048.png
convert qiro-logo_4096x4096.png -resize 2048x2048 qiro-logo_2048x2048.jpg
convert qiro-logo_4096x4096.png -resize 1024x1024 qiro-logo_1024x1024.png
convert qiro-logo_4096x4096.png -resize 1024x1024 qiro-logo_1024x1024.jpg
convert qiro-logo_4096x4096.png -resize 512x512 qiro-logo_512x512.png
convert qiro-logo_4096x4096.png -resize 512x512 -flatten qiro-logo_512x512.jpg
convert qiro-logo_4096x4096.png -resize 256x256 qiro-logo_256x256.png
convert qiro-logo_4096x4096.png -resize 256x256 -flatten qiro-logo_256x256.jpg
convert qiro-logo_4096x4096.png -resize 32x32 -flatten favicon.ico

echo "All done"
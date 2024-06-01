#!/usr/bin/env bash
cd "$(dirname "$0")" || exit 1

APP_NAME=AlchemApp
jpackage --input build/libs \
         --name "$APP_NAME" \
         --main-jar app.jar \
         --type app-image \
         --vendor alchem-x \
         --app-version 1.0.0 \
         --dest build \
         --icon icons/Jvav.icns \
         --mac-package-identifier alchem.app \
         --java-options -Xms20m \
         --java-options -Xmx20m \
         --java-options -DAPP=UI \
         --java-options -Dapple.awt.UIElement=true \
         --java-options -Dapple.awt.application.name="$APP_NAME" \
         --verbose


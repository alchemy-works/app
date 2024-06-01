#!/usr/bin/env bash
cd "$(dirname "$0")" || exit 1

if [ -f .env ]; then
    . .env
fi

export APP=CLI

exec java -Xms20m -Xmx20m -jar build/libs/app.jar "$@"

#!/bin/bash

# Script para compilar y generar el APK Release

echo "🔨 Iniciando compilación del APK..."
echo "=================================="

# Limpiar build anterior
echo "🧹 Limpiando builds anteriores..."
./gradlew clean

# Compilar APK Release
echo "📦 Compilando APK Release..."
./gradlew assembleRelease -x lint

# Verificar si se compiló correctamente
if [ -f "app/build/outputs/apk/release/app-release.apk" ]; then
    echo ""
    echo "✅ ¡APK COMPILADO EXITOSAMENTE!"
    echo "=================================="
    echo ""
    echo "📁 Ubicación del APK:"
    echo "   app/build/outputs/apk/release/app-release.apk"
    echo ""
    echo "📊 Información del APK:"
    ls -lh app/build/outputs/apk/release/app-release.apk
    echo ""
    echo "📱 Para instalar en tu teléfono:"
    echo "   adb install app/build/outputs/apk/release/app-release.apk"
    echo ""
else
    echo ""
    echo "❌ Error: No se pudo compilar el APK"
    echo "Verifica los logs anteriores para más detalles"
    exit 1
fi

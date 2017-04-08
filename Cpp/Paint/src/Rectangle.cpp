#include "Rectangle.h"

Rectangle::Rectangle(float size, sf::Color color) {
    this->brushSize = size;
    this->brushColor = color;
}

Rectangle::~Rectangle(){
    //dtor
}

void Rectangle::setBrushColor(sf::Color color) {
    this->brushColor = color;
}

void Rectangle::setBrushSize(float size) {
    this->brushSize = size;
}

sf::RectangleShape Rectangle::getRectangle() {
    sf::RectangleShape rect(sf::Vector2f(this->brushSize, this->brushSize));
    rect.setFillColor(this->brushColor);
    rect.setOrigin(this->brushSize/2, this->brushSize/2);

    return rect;
}

#include "Circle.h"

Circle::Circle(float radius, sf::Color color) {
    this->brushSize = radius;
    this->brushColor = color;

}

Circle::~Circle(){
    //dtor
}

void Circle::setBrushColor(sf::Color color) {
    this->brushColor = color;
}

void Circle::setBrushSize(float radius) {
    this->brushSize = radius;
}

sf::CircleShape Circle::getCircle() {
    sf::CircleShape circ(this->brushSize);
    circ.setFillColor(this->brushColor);
    circ.setOrigin(this->brushSize/2, this->brushSize/2);

    return circ;
}

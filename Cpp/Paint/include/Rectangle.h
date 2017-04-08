#ifndef RECTANGLE_H
#define RECTANGLE_H

#include <TGUI/TGUI.hpp>
#include <vector>

class Rectangle
{
    public:
        Rectangle(float size, sf::Color color);
        virtual ~Rectangle();

        void setBrushSize(float size);
        void setBrushColor(sf::Color color);
        sf::RectangleShape getRectangle();


    private:
        //sf::RectangleShape brush(sf::Vector2f(0,0));
        float brushSize;
        sf::Color brushColor;
        std::vector < int > sizes;
};

#endif // RECTANGLE_H

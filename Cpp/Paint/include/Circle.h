#ifndef CIRCLE_H
#define CIRCLE_H

#include <TGUI/TGUI.hpp>


class Circle
{
    public:
        Circle(float radius, sf::Color color);
        virtual ~Circle();

        void setBrushSize(float radius);
        void setBrushColor(sf::Color color);
        sf::CircleShape getCircle();


    private:
        //sf::RectangleShape brush(sf::Vector2f(0,0));
        float brushSize;
        sf::Color brushColor;
        std::vector < int > sizes;
};

#endif // CIRCLE_H

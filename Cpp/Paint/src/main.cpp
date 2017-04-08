#include <TGUI/TGUI.hpp>
#include "Rectangle.h"
#include "Circle.h"
#include <vector>
#include <string>
#include <sstream>
#include "Brush.h"

int main(){

    std::vector < sf::CircleShape > colorSelection;
    std::vector < sf::CircleShape > drawings;
    std::vector < sf::Vertex > pen;

    bool squareSelected = true;
    bool circleSelected = false;
    bool penSelected = false;
    bool triangleSelected = false;
    bool octagonSelected = false;
    bool eraserSelected = false;
    bool borderSelected = false;
    bool backgroundSelected = false;
    bool brushSelected = true;
    bool drawGrid = false;
    int xCoord = 0;
    int yCoord = 0;
    unsigned int i;

    sf::Color backgroundColor = sf::Color::White;
    sf::Color brushColor = sf::Color::Blue;

    sf::VideoMode resolution = sf::VideoMode().getDesktopMode();
    sf::RenderWindow window(resolution, "Paint", sf::Style::Fullscreen);
    sf::Image screenshot;


    //boundary lines
    sf::VertexArray lines(sf::Lines, 4);
    lines[0].position = sf::Vector2f(75, 75);
    lines[1].position = sf::Vector2f(resolution.width, 75);
    lines[2].position = sf::Vector2f(75, 74);
    lines[3].position = sf::Vector2f(75, resolution.height);
    lines[0].color = sf::Color::Black;
    lines[1].color = sf::Color::Black;
    lines[2].color = sf::Color::Black;
    lines[3].color = sf::Color::Black;

    sf::VertexArray boundaries(sf::Quads, 8);
    boundaries[0].position = sf::Vector2f(0, 0);
    boundaries[1].position = sf::Vector2f(resolution.width, 0);
    boundaries[2].position = sf::Vector2f(resolution.width, 75);
    boundaries[3].position = sf::Vector2f(0, 75);
    boundaries[4].position = sf::Vector2f(0, 0);
    boundaries[5].position = sf::Vector2f(75, 0);
    boundaries[6].position = sf::Vector2f(75, resolution.height);
    boundaries[7].position = sf::Vector2f(0, resolution.height);
    boundaries[0].color = sf::Color::White;
    boundaries[1].color = sf::Color::White;
    boundaries[2].color = sf::Color::White;
    boundaries[3].color = sf::Color::White;
    boundaries[4].color = sf::Color::White;
    boundaries[5].color = sf::Color::White;
    boundaries[6].color = sf::Color::White;
    boundaries[7].color = sf::Color::White;

    // attach gui to window
    tgui::Gui gui{window};

    tgui::Button::Ptr button = tgui::Button::create();
    button->setPosition(175, 25);
    button->setText("Clear");
    button->setSize(100, 40);
    button->connect("pressed", [&](){ drawings.clear(); });
    gui.add(button);


    sf::IntRect rectangle;
    rectangle.height = resolution.height - 75;
    rectangle.width = resolution.width + 75;

    // copy to image
    sf::Texture tex;
    tex.create(resolution.width, resolution.height);
    sf::Image win;

    sf::IntRect r1(75,75, resolution.width, resolution.height);


    std::stringstream xstr;
    xstr << xCoord;
    std::stringstream ystr;
    ystr << yCoord;
    sf::String x = xstr.str();
    sf::String y = ystr.str();

    tgui::Label::Ptr coordinates = tgui::Label::create();
    coordinates->setPosition(50, 1000);
    coordinates->setText(x + " " + y);
    coordinates->setSize(30, 10);
    gui.add(coordinates);

    tgui::Button::Ptr save = tgui::Button::create();
    save->setPosition(50, 25);
    save->setText("Save Image");
    save->setSize(100, 40);
    save->connect("pressed", [&]() { win = window.capture(); tex.loadFromImage(win, r1); screenshot = tex.copyToImage(); screenshot.saveToFile("masterpiece.png");});
    gui.add(save);

    tgui::Button::Ptr close = tgui::Button::create();
    close->setPosition(resolution.width-55, (resolution.height+15)-(resolution.height));
    close->setText("X");
    close->setSize(35, 35);
    close->connect("pressed", [&](){ window.close(); });
    gui.add(close);


    tgui::Slider::Ptr slider = tgui::Slider::create();
    slider->setPosition(30, 200);
    slider->setSize(20, 200);
    slider->setMaximum(75);
    slider->setMinimum(5);
    gui.add(slider);

    tgui::Button::Ptr undo = tgui::Button::create();
    undo->setPosition(300, 25);
    undo->setText("Undo");
    undo->setSize(100, 40);
    undo->connect("pressed", [&]() { if (penSelected==false && drawings.size() > 0) { if (drawings.size() > 10) {for (int i = 0; i < 10; i++) {drawings.pop_back();}} else { drawings.pop_back();} } else if (penSelected && pen.size() > 0) {pen.pop_back();} });
    gui.add(undo);

    tgui::Button::Ptr squareBrush = tgui::Button::create();
    squareBrush->setPosition(425, 25);
    squareBrush->setSize(100, 40);
    squareBrush->setText("Square");
    squareBrush->connect("pressed", [&](){ squareSelected=true; circleSelected=false; penSelected=false; });
    gui.add(squareBrush);

    tgui::Button::Ptr circleBrush = tgui::Button::create();
    circleBrush->setPosition(550, 25);
    circleBrush->setSize(100, 40);
    circleBrush->setText("Circle");
    circleBrush->connect("pressed", [&](){ squareSelected=false; circleSelected=true; penSelected=false;});
    gui.add(circleBrush);

    tgui::Button::Ptr penTool = tgui::Button::create();
    penTool->setPosition(675, 25);
    penTool->setSize(100, 40);
    penTool->setText("Pen");
    penTool->connect("pressed", [&](){ squareSelected=false; circleSelected=false; penSelected=true;});
    gui.add(penTool);

    tgui::Button::Ptr gridButton = tgui::Button::create();
    gridButton->setPosition(30, 80);
    gridButton->setSize(30, 30);
    gridButton->setText("Grid");
    gridButton->connect("pressed", [&](){ if (drawGrid) {drawGrid = false;} else { drawGrid = true;} });
    gui.add(gridButton);

    tgui::CheckBox::Ptr background = tgui::CheckBox::create();
    tgui::CheckBox::Ptr brush = tgui::CheckBox::create();
    background->setPosition(1350, 20);
    background->setSize(30, 30);
    background->setText("Background Color");
    background->connect("checked", [&]() { brush->uncheck(); backgroundSelected=true; brushSelected=false;});
    gui.add(background);

    brush->setPosition(1600, 20);
    brush->setSize(30, 30);
    brush->setText("Brush Color");
    brush->check();
    brush->connect("checked", [&]() { background->uncheck(); backgroundSelected=false; brushSelected=true;});
    gui.add(brush);


    // color selection buttons //
    Brush *blue = new Brush(25, sf::Color::Blue);
    Brush *green = new Brush(25, sf::Color::Green);
    Brush *yellow = new Brush(25, sf::Color::Yellow);
    Brush *black = new Brush(25, sf::Color::Black);
    Brush *white = new Brush(25, sf::Color::White);
    Brush *red = new Brush (25, sf::Color::Red);
    Brush *magenta = new Brush(25, sf::Color::Magenta);
    Brush *cyan = new Brush(25, sf::Color::Cyan);
    colorSelection.push_back(blue->getSquare());
    colorSelection.push_back(green->getSquare());
    colorSelection.push_back(yellow->getSquare());
    colorSelection.push_back(black->getSquare());
    colorSelection.push_back(white->getSquare());
    colorSelection.push_back(red->getSquare());
    colorSelection.push_back(magenta->getSquare());
    colorSelection.push_back(cyan->getSquare());


    // Ability to draw a grid
    sf::VertexArray gridLat(sf::Lines, (resolution.height/12.5));
    for(int i = 6; i < (resolution.height/12.5); i += 2){
        gridLat[i - 6].position = sf::Vector2f(75, (12.5 * i));
        gridLat[i - 6].color = sf::Color(0,0,0,100);
        gridLat[i - 5].position = sf::Vector2f(resolution.width, (12.5 * i));
        gridLat[i - 5].color = sf::Color(0,0,0,100);
    }
    sf::VertexArray gridLong(sf::Lines, (resolution.width/12.5));
    for(int i = 6; i < (resolution.width/12.5); i += 2){
        gridLong[i - 6].position = sf::Vector2f((12.5 * i), 75);
        gridLong[i - 6].color = sf::Color(0,0,0,100);
        gridLong[i - 5].position = sf::Vector2f((12.5 * i), resolution.height);
        gridLong[i - 5].color = sf::Color(0,0,0,100);
    }

    tgui::Button::Ptr brushColorBlue = tgui::Button::create();
    brushColorBlue->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Blue;} else {backgroundColor=sf::Color::Blue;}});
    brushColorBlue->setPosition(832, 20);
    brushColorBlue->setOpacity(0);
    brushColorBlue->setSize(35, 35);
    gui.add(brushColorBlue);

    tgui::Button::Ptr brushColorGreen = tgui::Button::create();
    brushColorGreen->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Green;} else {backgroundColor=sf::Color::Green;}});
    brushColorGreen->setPosition(882, 20);
    brushColorGreen->setOpacity(0);
    brushColorGreen->setSize(35, 35);
    gui.add(brushColorGreen);

    tgui::Button::Ptr brushColorYellow = tgui::Button::create();
    brushColorYellow->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Yellow;} else {backgroundColor=sf::Color::Yellow;}});
    brushColorYellow->setPosition(932, 20);
    brushColorYellow->setOpacity(0);
    brushColorYellow->setSize(35, 35);
    gui.add(brushColorYellow);

    tgui::Button::Ptr brushColorBlack = tgui::Button::create();
    brushColorBlack->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Black;} else {backgroundColor=sf::Color::Black;}});
    brushColorBlack->setPosition(982, 20);
    brushColorBlack->setOpacity(0);
    brushColorBlack->setSize(35, 35);
    gui.add(brushColorBlack);


    tgui::Button::Ptr brushColorWhite = tgui::Button::create();
    brushColorWhite->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::White;} else {backgroundColor=sf::Color::White;}});
    brushColorWhite->setPosition(1032, 20);
    brushColorWhite->setOpacity(0);
    brushColorWhite->setSize(35, 35);
    gui.add(brushColorWhite);

    tgui::Button::Ptr brushColorRed = tgui::Button::create();
    brushColorRed->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Red;} else {backgroundColor=sf::Color::Red;}});
    brushColorRed->setPosition(1082, 20);
    brushColorRed->setOpacity(0);
    brushColorRed->setSize(35, 35);
    gui.add(brushColorRed);

    tgui::Button::Ptr brushColorMagenta = tgui::Button::create();
    brushColorMagenta->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Magenta;} else {backgroundColor=sf::Color::Magenta;}});
    brushColorMagenta->setPosition(1132, 20);
    brushColorMagenta->setOpacity(0);
    brushColorMagenta->setSize(35, 35);
    gui.add(brushColorMagenta);

    tgui::Button::Ptr brushColorCyan = tgui::Button::create();
    brushColorCyan->connect("pressed", [&](){ if (brushSelected) {brushColor = sf::Color::Cyan;} else {backgroundColor=sf::Color::Cyan;}});
    brushColorCyan->setPosition(1182, 20);
    brushColorCyan->setOpacity(0);
    brushColorCyan->setSize(35, 35);
    gui.add(brushColorCyan);


    Brush *brushStroke = new Brush(slider->getValue(), brushColor);
    float slideNum = slider->getValue();
    sf::CircleShape stroke;
    sf::CircleShape eraserShape = brushStroke->getCircle();


    while(window.isOpen()) {
        sf::Event e;
        while (window.pollEvent(e)) {
            if (e.type == sf::Event::Closed) {
                window.close();
            }
            gui.handleEvent(e);
        }
        sf::Vector2i mouse = sf::Mouse::getPosition(window);
        xCoord = mouse.x;
        yCoord = mouse.y;
        sf::CircleShape cursor;

        if(xCoord < 75 || yCoord < 75){ //mouse is on border making selections
            window.setMouseCursorVisible(true);
            slideNum = slider->getValue();
            brushStroke->setBrushSize(slideNum);
            if (squareSelected) {
                stroke = brushStroke->getSquare();
                stroke.setRotation(45);
            }
            else if (circleSelected) {
                stroke = brushStroke->getCircle();
            }

        }
        else{ //mouse is on canvas making art
            window.setMouseCursorVisible(false);

            // sets cursor to be a brush
            if (squareSelected) {
                brushStroke->setBorder(2, sf::Color::Black);
                brushStroke->setBorderStatus(true);
                cursor = brushStroke->getSquare();
                cursor.setRotation(45);
                window.setMouseCursorVisible(false);
                window.draw(cursor);
                brushStroke->setBorderStatus(false);
            }
            else if (circleSelected) {
                float slideNum = slider->getValue();
                sf::Vector2i mouse = sf::Mouse::getPosition(window);
                brushStroke->setBrushSize(slideNum);
                brushStroke->setBrushColor(brushColor);
                brushStroke->setBorderStatus(true);
                brushStroke->setBorder(2, sf::Color::Black);
                cursor = brushStroke->getCircle();
                cursor.setPosition((float)mouse.x, (float)mouse.y);
                window.setMouseCursorVisible(false);
                window.draw(cursor);
                brushStroke->setBorderStatus(false);
            }
            brushStroke->setBrushColor(brushColor);
            window.draw(cursor);
            brushStroke->setBorderStatus(false);
            if (sf::Mouse::isButtonPressed(sf::Mouse::Left)) {
                // Left click draws current brush
                stroke.setPosition((float)mouse.x, (float)mouse.y);
                drawings.push_back(stroke);
            }
            else if (sf::Mouse::isButtonPressed(sf::Mouse::Right)) {
                // Set right click to "erase"
                brushStroke->setBrushColor(backgroundColor);
                eraserShape.setPosition((float)mouse.x, (float)mouse.y);
                drawings.push_back(eraserShape);
            }
        }

        window.clear(backgroundColor);
        window.draw(cursor);

        // Draw vector of shapes
        for (i = 0; i < drawings.size(); i++) {
            window.draw(drawings[i]);
        }

        // Draw grid lines
        if (drawGrid) {
            window.draw(gridLat);
            window.draw(gridLong);
        }

        // draw color selection buttons
        float x = 850;
        float y = 20;

        window.draw(boundaries);
        window.draw(lines);
        for (i = 0; i < colorSelection.size(); i++) {
            colorSelection[i].setPosition(x, y);
            colorSelection[i].setRotation(45);
            colorSelection[i].setOutlineColor(sf::Color::Black);
            colorSelection[i].setOutlineThickness(3);
            x+=50;
            window.draw(colorSelection[i]);
        }
        gui.draw();
        window.display();
    }
    return 0;
}

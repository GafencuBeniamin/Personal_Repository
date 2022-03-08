#pragma once
#include <string>
#include "Custom_Player.h"

class Tank : public Custom_Player{
protected:
    float magic_resist;
    float armor;

public:
    //setters and getters
    void set_mr(float mr);
    float get_mr();
    void set_armor(float arm);
    float get_armor();

    //replica
    void reply();

    //constructor
    Tank();

    //destructor
    ~Tank();

};


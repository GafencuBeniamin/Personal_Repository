#pragma once
#include "Mage.h"
#include "Dark_Mage.h"
#include <string>

class Witch : public Mage{

private:
    bool broom;
public:
    //setters and getters
    void set_broom(bool y);
    bool get_broom();
    //constructor
    Witch();
    //destructor
    ~Witch();
    //replica
    void witchsaying();

};


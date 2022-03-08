#include <iostream>
#include "Witch.h"

//setters and getters
void Witch::set_broom(bool y) { broom=y;}
bool Witch::get_broom() { return broom; }

//constructor
Witch::Witch() {
    broom=0;
}

//destructor
Witch::~Witch() {}

//replica
void Witch::witchsaying() {
    if (broom)
        std::cout<<"A witch said: I have a broom and i can fly\n";
    else std::cout<<"A witch said: I don't have my broom\n";
}



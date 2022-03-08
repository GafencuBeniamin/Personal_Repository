#include "Tank.h"
#include <iostream>

// setters and getters

//for armor
void Tank::set_armor(float arm) { armor=arm; }
float Tank::get_armor() { return armor; }

//for magic resist
void Tank::set_mr(float mr) { magic_resist=mr; }
float Tank::get_mr() { return magic_resist; }

//reply
void Tank::reply() { std::cout<<"A tank said: Hello, I am a tank\n"; }

//constructor
Tank::Tank() {
    magic_resist=200;
    armor=150;
}
//destructor
Tank::~Tank() {}

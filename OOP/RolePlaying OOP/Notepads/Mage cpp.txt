#include "Mage.h"
#include <iostream>

// setters and getters

//for ability power
void Mage::set_ap(float ap) { ability_power=ap; }
float Mage::get_ap() { return ability_power; }

//for magic penetration
void Mage::set_mp(float mp) { magic_penetration=mp; }
float Mage::get_mp() { return magic_penetration; }

//constructor
Mage::Mage(){
    ability_power=100;
    magic_penetration=50;
}
//destructor
Mage::~Mage() {}

//replica
void Mage::reply() {std::cout<<"A mage said: Hello, I am a mage \n"; }

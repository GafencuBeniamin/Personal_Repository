#include "Dark_Mage.h"
#include <iostream>

//setters and getters

//for poison
void Dark_Mage::set_poison(float p) { poison=p; }
float Dark_Mage::get_poison() { return poison; }

//for evilness
void Dark_Mage::set_evilness(float e) { evilness=e; }
float Dark_Mage::get_evilness() { return evilness; }

//for crimes
float Dark_Mage::get_crime() { return crimes; }

//replica
void Dark_Mage::reply() { std::cout<<"A dark mage just said: Muhahahah! I am a dark mage\n"; }

//crime contor
void Dark_Mage::crimeadd() {crimes++;}

//constructor
Dark_Mage::Dark_Mage() {
    poison=50;
    evilness=0;
    crimes=0;
}
//destructor
Dark_Mage::~Dark_Mage() {}

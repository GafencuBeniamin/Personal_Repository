#include <iostream>
#include "Custom_Player.h"
#include <string>

//setters and getters


//for health
void Custom_Player::set_hp(float health){ hp=health; }
float Custom_Player::get_hp () { return hp; }

//for experience
void Custom_Player::set_xp(float experience){ xp=experience; }
float Custom_Player::get_xp (){ return xp; }

//for mana
void Custom_Player::set_mana(float energy) { mana=energy; }
float Custom_Player::get_mana() { return mana; }

//for base stats
void Custom_Player::set_stats(float health, float energy) {
    stats.maxhp=health;
    stats.maxmana=energy;
}
Statistics Custom_Player::get_stats() { return stats; }

//inheritance functions
void Custom_Player::jump () { std::cout << "A player just jumped\n"; }
void Custom_Player::talk (std::string message) { std::cout<<"A player just said: "<<message<<"\n"; }

//constructor
Custom_Player::Custom_Player() {
    stats.maxmana=100;
    stats.maxhp=100;
    hp=100;
    mana=100;
    xp=0;
}
//destructor
Custom_Player::~Custom_Player() {}

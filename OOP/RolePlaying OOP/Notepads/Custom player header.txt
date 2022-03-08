#pragma once
#include <string>
#include "Character.h"

//Clasa compozitie de la punctul 2)

class Statistics {
public:
    float maxhp;
    float maxmana;
};

class Custom_Player : public Character {
protected:
    Statistics stats;
    float hp;
    float xp;
    float mana;
public:

    //setters and getters
    void set_hp(float health);
    float get_hp ();
    void set_xp(float experience);
    float get_xp ();
    void set_mana(float energy);
    float get_mana ();
    void set_stats (float health, float energy);
    Statistics get_stats ();

    //inheritance functions
    virtual void jump ();
    virtual void talk (std::string message);

    //constructor
    Custom_Player();

    //destructor
    virtual ~Custom_Player();
};

#pragma once
#include "Mage.h"
#include <string>
#include "Witch.h"

class Dark_Mage : public Mage{
    //friends
    friend class Witch;
private:
    float poison;
    float evilness;
    float crimes;
public:
    //setters and getters
    void set_poison(float p);
    float get_poison();
    void set_evilness(float e);
    float get_evilness();
    float get_crime();

    //replica
    virtual void reply() override;

    //crime contor
    void crimeadd();

    //constructor
    Dark_Mage();
    //destructor
    virtual ~Dark_Mage();
};




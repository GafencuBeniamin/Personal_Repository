#include <iostream>
#include "Custom_Player.h"
#include "Tank.h"
#include "Mage.h"
#include "Dark_Mage.h"
#include "Witch.h"

int main() {
    //interfata este Character

    //Clasa A
    Custom_Player Robert;
    Robert.talk("Hi i am a custom player \n");

    //Clasa C
    Tank John;
    John.reply();
    John.set_stats(150,100);
    std::cout<<"A tank just got buffed with hp for being a tank: "<<John.get_stats().maxhp<<" total hp\n";
    John.jump();

    std::cout<<"\n";

    //Clasa B
    Mage Michael;
    Michael.reply();
    Michael.set_stats(100,150);
    std::cout<<"A mage just got buffed with mana for being a mage: "<<Michael.get_stats().maxmana<<" total mana\n";
    Michael.jump();

    std::cout<<"\n";

    //Clasa derivata din B
    Dark_Mage Angie;
    Angie.set_ap(100);
    Angie.reply();
    std::cout<<"I cant test my "<<Angie.get_ap()<<" ability power i just got\n";
    std::cout<<"A dark mage killed a citizen\n";
    Angie.crimeadd();
    std::cout<<"The dark mage crime count is now: "<<Angie.get_crime()<<"\n";
    std::cout<<"A dark mage said: Now i have to escape\n\n";

    //Clasa prietena cu clasa derivata din B
    Witch Annie;
    Annie.set_broom(1);
    Annie.witchsaying();
    return 0;
}

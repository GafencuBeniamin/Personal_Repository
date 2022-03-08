#pragma once

class Character {
public:
    virtual void jump () = 0;
    virtual void talk (std::string message) =0;
    virtual ~Character() {}
};




class Transportation:
    wheels = 0

    def __init__(self):
        self.wheels = -1

    def travel_one(self):
        print("Travelling on generic transportation")

    def travel(self, distance):
        for _ in range(distance):
            self.travel_one()

    def is_auto(self):
        return self.wheels == 4

class Bike(Transportation):

    def travel_one(self):
        print("Biking one mile")

class Car(Transportation):
    wheels = 4

    def travel_one(self):
        print("Driving one mile")

    def make_sound(self):
        print("VROOM")

class Ferrari(Car):
    pass

t = Transportation()
b = Bike()
c = Car()
f = Ferrari()


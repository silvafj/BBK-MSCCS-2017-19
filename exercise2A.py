# CODE for EXERCISE 2, Part A
# ---------------------------
# This exercise introduces  
#   * The label widget (part A)
#   * The entry widget (part B)
#
# The exercise explores setting and getting attributes

from tkinter import *

# Create the top level window
app = Tk()
app.title("GUI Example")
app.geometry('200x100')

# MODIFY THIS METHOD
# This method should update the label text when the button is pressed
#    * If the text is "Text A", change it to Text B
#    * If the text is "Text B", change it to Text A
# This means reading and updating the text attribute of the Label
def changeLabelText():
    print("The current text is", l1['text']) # get an attribute value
    l1['text'] = "Changed Text"              # set an attribute
        
# Create a button
b1 = Button(app, text="Change Text", command=changeLabelText)

# Create a label
l1 = Label(app, text="Text")

# Pack the three widget into the window
l1.pack(side='bottom')
b1.pack(side='bottom')


app.mainloop()

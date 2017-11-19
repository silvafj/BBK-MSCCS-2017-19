# CODE for EXERCISE 2B
# -------------------
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
# This method should 
#    * check if text has been entered
#    * copy it to the label
# This means updating the test attribute of the Label
def copyTextToLabel():
    print(v.get()) # This shows how to get the string from v

# Create a button
b1 = Button(app, text="Copy Text", command=copyTextToLabel)

# Create a label
l1 = Label(app, text="Text is displayed here")

# A StringVar is a special variable that can be used to
# hold the string entered in an Entry widget
#
# Use v.get() to get the string from the variable
v = StringVar()

# Create an entry widget
e1 = Entry(app, textvariable = v)

# Pack the three widget into the window
l1.pack(side='bottom')
b1.pack(side='bottom')
e1.pack(side='bottom')

app.mainloop()

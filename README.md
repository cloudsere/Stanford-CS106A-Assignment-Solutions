# Stanford-CS106A-Assignment-Solutions

### Assignment 6

![assignment6-demo](https://github.com/cloudsere/Stanford-CS106A-Assignment-Solutions/blob/master/doc/pics/assignment6.gif?raw=true)



This program is used for searching a US baby name. User will get the rank of a name every decade from 1900 to 2000. There will be a line chart to show all the data. User can search for multiple names and display all of them using line chart. In my version, I add a delete button for each name, user can click on a button with a  name to delete the corresponding line chart.

It's a very interesting program, I want to share something I found need to consider when coding.

#### 1 Prevent to draw a same name second time

What if the user clicks on the "Graph" button twice with the same name?

If you don't do anything, you may probably draw a same line chart with different color from the previous one. The new line chart will overlap the old one. If you draw too many same line charts, the line will become bolder.

I handle this by do some check when use `addEntry`method

```javascript
public Boolean addEntry(NameSurferEntry entry) {
		int index = indexOfNameSurfer(entry.getName());
		/* Prevent from adding a same name multiple times */
		if(index < 0) {
			nameInGraph.add(entry);
			nameColor.add(generateColor());
			return true;
		}
		return false;
	}
```

I create a new private method called `indexOfNameSurfer`, it takes in a name as parameter and search for the index of the corresponding entry in the arrayList of all the entries which have been added. If the name is not in the arrayList, `indexOfNameSurfer` will return -1, just as the normal `indexOf` does.

```javascript
private int indexOfNameSurfer(String name) {
		for(int i = 0; i< nameInGraph.size(); i++) {
			if(name.equals(nameInGraph.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}
```



#### 2 Keep track of what color to use.

In the assignment handout, I read this:

> … you need to make sure that, if you added a bunch of entries to the graph and then deleted the early ones, the colors of the later entries remain the same.



It's first difficult to me. I thought I could use a different arrayList for the colors, but I don't know how to keep track of the right color when the user delete an entry.

Finally I found a way to deal with this. I use previous color to generate the next color:

```javascript
private Color generateColor() {
		int length = nameColor.size();
		if(length == 0) return colorArray[0];
		
		Color lastColor = nameColor.get(length-1);
		int lastColorIndex = 0;
		for(int i = 0; i<colorArray.length; i++) {
			if(lastColor == colorArray[i]) {
				lastColorIndex = i;
				break;
			}
		}
		Color curColor = colorArray[(lastColorIndex+1)%3];
		return curColor;
	}
```




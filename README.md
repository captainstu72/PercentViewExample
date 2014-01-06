PercentViewExample
==================

This is my adaptation of the answer given on http://stackoverflow.com/questions/19731261/android-draw-circle-with-2-colors-pie-chart by Sherif elKhatib.

It allows the user to select a normal pie mode, or an outline mode - which only marks the percentage around the edge. I have included my edits to Sherif's code for that fix the issue of the background not showing.
It will show the percentage in 3 different colours based on the percentage. These can be over ridden by using the setHighColour, setMedColour, and setLowColour. You can change the background with setBackgroundColour. 

Usage:

Layout:

```xml
<path.to.your.class.PercentView
    android:id="@+id/percentView1"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_alignParentLeft="true"
    android:layout_centerInParent="true"/>
```

Code:
```java
       int myPercentage = 55;

       PercentView pv = (PercentView) findViewById (R.id.percentView1);
       pv.setPercentage(myPercentage);
       pv.setOutlineSize(10); // sets the width of the outline mode - default value if not defined is 10.
       pv.setOutlineOnly(false); //set to true to use the outline, otherwise it will be a standard pie.
       pv.setBackgroundColour(this.getResources().getColor(R.color.cardsLightBackground)); //fully qualify your colours to override
       pv.setHighColour(this.getResources().getColor(R.color.flatgreen));
       pv.setMedColour(this.getResources().getColor(R.color.flatyellow));
       pv.setLowColour(this.getResources().getColor(R.color.flatred));
```
For the colours, if you are not using resources, you can supply a colour from aRGB hex using: Color.parseColor("#ff57d78d");

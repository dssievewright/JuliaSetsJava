<%@ include file="includeTags.jsp" %>
<h2>What are these pictures?</h2>
<h3>Complex Numbers Basics</h3>
<h4>The Complex Plane</h4>
<div>
	Let's start with a (very) brief review of complex numbers.
	Recall that a complex number is a number of the form \(z = a + bi\) where \(a\) and \(b\) are 
	real numbers and \(i\) is the imaginary unit, i.e., \(i^2 = -1\).  
	Since these complex numbers depend on two real numbers, it is often very helpful to think
	of complex numbers as points \((a,b)\) in Cartesian plane.
</div>

<div class="text-center">
	<img alt="Complex Plane Points" style="width:320px" src="${pageContext.request.contextPath}/tex/complexPlane.svg">
</div>

<div>
	The \(x\)-axis is called the real axis while the \(y\)-axis is called the 
	imaginary axis and these axes are labeled above as Re and Im, respectively.  
	Furthermore, we use shorthand notation Re\((z)\) and Im\((z)\)
	to refer to the coordinates along the real and imaginary axes.  
	For example, if \(z=1+2i\), then Re\((z) = 1\) and Im\((z) = 2\).
</div>
<br>
<h4>Modulus</h4>
<div>
	The last thing to dive into is the <emph>Modulus</emph> of a complex number.
	In simplest terms, the modulus of \(z=a+bi\) is the distance from the origin
	to the point \((a,b)\) in the Cartesian plane.  In other words, we can refer to it
	as the size of a complex number.  The analog with real numbers would be
	the absolute value function which represents the distance from a number to 0
	along the real number line. We even use the same notation.  If 
	\(z=a+bi\), then the modulus of z is 
	\[|z| = \sqrt{a^2+b^2}.\]
</div>

<h3>Function Iteration</h3>

<div>
	Now let's start digging into the details of a Julia Set.  We'll start off
	with a function, for example \(f(z) = z^2 - 0.4 + 0.6i\).  One nice property 
	is that this function is defined everywhere, so regardless of our choice of
	complex number \(z\), we can always apply our function \(f\).  That means we
	can <emph>iteratively</emph> apply our function over and over to any complex 
	number.  For example,
	
	\begin{align*}
		f(0) 			&= 0^2 - 0.4 + 0.6i\\	
		     			&= -0.4+0.6i\\
		f(f(0)) 		&= f(-0.4+0.6i) \\
						&= (-0.4 + 0.6i)^2 - 0.4 +0.6i\\
						&= ... \mbox{(let's skip the boring details)}\\
						&= 0.12 + 0.12 i\\
		f(f(f(0)))  &= f(0.12 + 0.12 i)\\
						&= -0.3712 + 0.6288i\\
		...
	\end{align*}
	
	For shorthand notation, we'll use \(f_n\) for the function that represents
	applying the starting function, \(f\), \(n\) times.  In other words,
	\(f_1(z) = f(z)\) and \(f_{n+1}(z) = f(f_n(z))\), for \(n=1,2, 3, \ldots\).
	 
	As we iteratively apply \(f\), one of two things can happen:
	<ul>
		<li>\(f_n(z)\) becomes arbitrarily large as \(n\) increases.</li>
		<li>There's an upper limit to how large \(f_n(z)\) 
				can get as \(n\) increases. </li>
	</ul>
	The Julia Set is then defined as the boundary of that first set.
</div>
<br>
<h3>An Example</h3>
<div>
	One of the easiest examples to understand is the quadratic function
	\[f(z) = z^2.\]
	Let's look at the first values of \(n\) to see if we can find a pattern to
	 \(f_n(z)\).  Starting with \(n=1\), that's just our starting function,
	\(f_1(z) = f(z) = z^2\).
	For \(n=2\), 
	
	\begin{align*}
		f_2(z) 	&= f(f(z)) 	\\
		 			&= f(z^2)	\\
		 			&= (z^2)^2	\\
		 			&= z^4,
	\end{align*}
	
	and for \(n=3\),
	
	\begin{align*}
		f_3(z) 	&= f(f_2(z)) 	\\
		 			&= f(z^4)	\\
		 			&= (z^4)^2	\\
		 			&= z^8.
	\end{align*}
	
	It looks like the pattern is that the exponent will double every time we 
	apply \(f\). In fact, we can write this as
	
	\[f_n(z) = z^{(2^n)}.\]
	
	So what happens to a complex number \(z\) as we keep increasing the exponent?
	It turns out that, in this case, we have very similar properties to real numbers.
	If \(|z| < 1\), then \(f_n(z)\) will keep getting smaller, converging to the 0.
	If \(|z| > 1\), then \(f_n(z)\) will keep getting larger, diverging off to infinity.
	Finally, if \(|z| = 1\), then \(|f_n(z)| = 1\), as well.
	Let's take a look back at the Complex plane with this breakdown.
</div>

<div class="text-center">
	<img alt="Julia set for z squared" style="width:320px" src="${pageContext.request.contextPath}/tex/JuliaSetForzSquared.svg">
</div>


<div>
	Above, the black circle is the unit circle described by the equation,  \(|z| = 1 \).  
	The points inside the unit circle, \(|z| < 1\), are colored gray and
	converge to zero as we iterate \(f\).  Meanwhile the points outside the 
	unit circle, \(|z| > 1\), are colored  blue and tend towards \(\infty\) as we iterate \(f\).
	This means that the Julia Set for \(f(z) = z^2\) is the boundary of the blue set.  
	In other words, the unit circle.
	
	
	
	<!-- In general, we 
	ask that \(f\) be any function of the form \(f(z) = \frac{p(z)}{q(z)}\) where
	\(p\) and \(q\) are polynomials.  -->
</div>

<h3>And how does this fit in with the pictures on the main page?</h3>

<div>
	On the main page, you'll see pictures with the colors blue, white, red, and black (assuming the default colors haven't been changed).  
	A point shaded blue requires very few iterations of \(f\) for us to determine that the point will tend towards \(\infty\).  
	Meanwhile, points shaded white require more iterations and points shaded red require even more.
	If we could apply infinitely many iterations of \(f\), then the Julia set of \(f\) would be the boundary between the black and non-black points.
	
	<br><br>
	
	The only functions this site currently looks at are functions of the form \(f(z) = z^2+c\).
	The value of \(c = a+bi\) is determined by the Real Component and Imaginary Component inputs that you can change.
	The iterations box is how many times the code will apply the function \(f\) to a specific point.
	The Upper Bound represents a modulus for us to determine if \(f_n(z)\) will tend towards \(\infty\).
	Specifically, each time we apply \(f\), we'll compare the modulus of the output to this Upper Bound.  If 
	the modulus is larger than the upper bound, we'll assign the point \(z\) a color of blue, white, or red depending on how many
	iterations of \(f\) we had to apply.  But if \(f_n(z)\) never exceeds that Upper Bound, for \(1\leq n \leq ) Iterations, then we assign
	the color black to the point.  If we want a good representation of the Julia Set,
	then we can increase the value of Iterations and the only requirement we need is that Upper Bound is larger than 2.  If you choose an upper bound
	smaller than 2, then some points inside the Julia Set might get assigned a non-black color when they shouldn't be.  But I won't stop you from trying
	this out so you can see what happens!
	
	<br>
	<br>
	
	The other input values you can changes are Axis Limits and the Resolution.
	The axis limits change what rectangle we're looking at in the complex plane.  The default is the square with vertices (-2,-2), (-2,2), (2,2), and (2,-2)
	and you can change these to zoom in or out as you desire.
	The resolution just controls the width and height of resulting image.  Each pixel in the image corresponds with a point \(z\) in the complex plane.
	
	
</div>



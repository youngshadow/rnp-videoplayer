/*
 * Copyright (C) 2009, Edmundo Albuquerque de Souza e Silva.
 *
 * This file may be distributed under the terms of the Q Public License
 * as defined by Trolltech AS of Norway and appearing in the file
 * LICENSE.QPL included in the packaging of this file.
 *
 * THIS FILE IS PROVIDED AS IS WITH NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 * INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 * FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 * WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */

package net.sf.fmj.utility;

import java.io.PrintStream;

/**
 * Utility functions for dumping stack traces.
 * 
 *
 */
public class ThreadUtils
{

	public static void printStackTrace(final StackTraceElement[] stack)
	{
		printStackTrace(stack, System.out);	// not to System.err because it is probably not an exception stack trace
	}
	public static void printStackTrace(final StackTraceElement[] stack, PrintStream out)
	{
		final StringBuffer b = new StringBuffer();
		printStackTrace(stack, b);
		out.print(b.toString());
	}
	
	public static void printStackTrace(final StackTraceElement[] stack, StringBuffer b)
	{
		for (int i = 0; i < stack.length; ++i)
		{
			StackTraceElement e = stack[i];
			b.append("\t at ");
			b.append(e.getClassName());
			b.append(".");
			b.append(e.getMethodName());
			b.append("(");
			b.append(e.getFileName());
			b.append(":");
			b.append(e.getLineNumber());
			b.append(")\n");
			
		}
		//b.append("\n");
	}
}

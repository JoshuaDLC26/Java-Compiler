// =================================================================================================================================
// IMPORTS

import java.util.List;
import java.util.Map;
// =================================================================================================================================



// =================================================================================================================================
/**
 * A print statement.
 */
class PrintStatement extends Statement {
// =================================================================================================================================


    
    // =============================================================================================================================
    // DATA MEMBERS

    /** The arguments for printing. The first must be a formatting string. */
    public final List<Expression> _args;
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Create a new print statement.
     *
     * @param position The position at which this statement begins in the source code.
     * @param args     The arguments for printing; the first must be a formatting string.
     */
    public PrintStatement (int position, List<Expression> args) {

        super(position);
	_args = args;
	
    } // PrintStatement ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Bind each variable used to its declaration.  Signal an error if no matching declaration is found.
     *
     * @param symbols The symbol table of variable declarations.
     */
    public void bind (Map<String, VariableDeclaration> symbols) {

	for (Expression arg : _args) {
	    arg.bind(symbols);
	}
	
    } // bind ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Verify semantic validity; determine operator based on operand list.  This method signals an error if an invalidity is found.
     * For print statements, the lead argument must be a string (char*).
     *
     * @return the type of data produced by executing the statement; <code>null</code> if no data is produced.
     */
    public Type verify () {

	// Sanity check.
	if (_args.isEmpty()) {
	    Utility.abort("PrintStatement.verify(): Unexpected empty argument list @" + _position);
	}

	// We currently can only handle arguments that fit into the argument passing registers.
	if (_args.size() > Utility._argumentRegisters.length) {
	    Utility.error("Too many arguments", _position);
	}

	// Verify the argument expressions themselves.
	for (Expression arg : _args) {
	    arg.verify();
	}

	// Verify that the lead argument is a string.
	Expression lead = _args.getFirst();
	if (!lead.getType().equals(new TypePointer(-1, new TypeCharacter(-1)))) {
	    Utility.error("Expected string", lead._position);
	}
	
        return null; // Print statements don't yield a value.
	
    } // verify ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Generate assembly code that will execute this statement.
     *
     * @return the generated assembly code.
     */
    public String toAssembly () {

	String assembly = "\t; Begin print statement @" + _position + '\n';

	// Get code for evaluating the arguments, doing so and pushing their results in reverse order.
	for (int i = _args.size() - 1; i >= 0; i -= 1) {
	    assembly += ( "\t;   Evaluating print arg[" + i + "]\n" +
			  _args.get(i).toAssembly() );
	}

	// Create code to place arguments to printf() into the argument registers.
	assembly += "\t;   Calling printf()\n";
	int i = 0;
	while (i < _args.size()) {
	    assembly += "\tpop\t" + Utility._argumentRegisters[i] + "\t\t; arg[" + i + "]\n";
	    i += 1;
	}
	assembly += ( "\tmov\trax,\t0\t; No vector varargs\n" +
		      "\tcall\tprintf\n"                      );
	
	assembly += "\t; End print statement @" + _position + '\n';

	return assembly;

    } // toAssembly ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Generate assembly code for any statics that are part of this statement.  For printing a string, add to the statics a
     * formatting string for this particular output.
     *
     * @return the generated assembly code.
     */
    public String toStatics () {

	String assembly = new String();
	for (Expression arg : _args) {
	    assembly += arg.toStatics();
	}
	return assembly;
	
    } // toStatics ()
    // =============================================================================================================================


    
    // =============================================================================================================================
    public String toString () {

	String string = "print( ";
	for (Expression arg : _args) {
	    string += arg.toString() + ' ';
	}
	string += ')';

	return string;
		
    } // toString ()
    // =============================================================================================================================


	
// =================================================================================================================================
} // class PrintStatement
// =================================================================================================================================

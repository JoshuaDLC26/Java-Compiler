// =================================================================================================================================
// IMPORTS

import java.util.Map;
// =================================================================================================================================



// =================================================================================================================================
/**
 * An operation on two operands that yields some value.
 */
public class MemoryAllocation extends Expression {
// =================================================================================================================================



    // =============================================================================================================================
    // DATA MEMBERS

    /** The operands on which to apply the memory allocation operator. */
    public final Expression _operand;

    /** The type of the memory allocation (a void*). */
    public final Type       _type;
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Create an operation.
     *
     * @param position The location in the source code at which the expression begins.
     * @param operand  The operand on which to apply the memory allocation.
     */
    public MemoryAllocation (int position, Expression operand) {

        super(position);
        _operand = operand;
	_type    = new TypePointer(position, new TypeVoid(position));

    } // Operation ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Bind each variable used to its declaration.  Signal an error if no matching declaration is found.
     *
     * @param symbols The symbol table of variable declarations.
     */
    public void bind (Map<String, VariableDeclaration> symbols) {

        _operand.bind(symbols);

    } // bind ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Verify that an integer was passed to the memory allocation
     */
    public Type verify () {

        // Verify that the operand is an integer.
        Type int_type = new TypeInteger(_position);
        Type op_type = _operand.verify();
        if(!int_type.equals(op_type)) Utility.error("Memory Allocation expression requires an integer operand", _operand._position);

        // Return a pointer to void*, already constructed.
	return _type;

    } // verify ()
    // =============================================================================================================================



    // =============================================================================================================================
    public Type getType () {

	return _type;

    } // getType ()
    // =============================================================================================================================



    // =============================================================================================================================
    public boolean hasReference () {

	return false;

    } // hasReference ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly code that will execute this expression.
     *
     * @return the generated assembly code.
     */
    public String toAssembly () {

	String assembly = new String();

	// Call malloc() to allocate memory on the heap.
	assembly += "\t; memory(): (A) Evaluate size\n";
	assembly += _operand.toAssembly();
	assembly += "\t; memory(): (B) Call malloc()\n";
	assembly += ( "\tpop\trdi\t\t; rdi = arg[0] (size)\n"           +
		      "\tcall\tmalloc\t\t\t; rax = allocated address\n" +
		      "\tpush\trax\t\t; Push allocated address\n"       );

	return assembly;

    } // toAssembly ()
    // =============================================================================================================================



    // =============================================================================================================================
    public String toString () {

        return "memory(" + _operand.toString() + ")";

    } // toString ()
    // =============================================================================================================================



// =================================================================================================================================
} // class Operation
// =================================================================================================================================

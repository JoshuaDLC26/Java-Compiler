// =================================================================================================================================
// IMPORTS
import java.util.List;
// =================================================================================================================================



// =================================================================================================================================
/**
 * A declared procedure.
 */
abstract public class Procedure {
// =================================================================================================================================


    
    // =============================================================================================================================
    // DATA MEMBERS

    // Token containing the name of this procedure
    public final Token _name;

    // Type corresponding to the return type of this procedure
    public final Type  _type;

    // List of parameters which this procedure assumes will be provided by the callee
    public final List<VariableDeclaration> _parameters;

    // List of variables which are declared locally within this procedure
    public final List<VariableDeclaration> _locals;
    // =============================================================================================================================


    
    // =============================================================================================================================
    /**
     * Basic constructor for a Procedure.
     *
     * @param name       The procedure's name.
     * @param type       The proecdure's return type.
     * @param parameters The parameters.
     * @param locals     The local variables.
     */
    public Procedure (Token name, Type type, List<VariableDeclaration> parameters, List<VariableDeclaration> locals){
	
        _name       = name;
        _type       = type;
        _parameters = parameters;
        _locals     = locals;
	
    } // Procedure ()
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly for this procedure.
     *
     * @return the code-segment assembly for this procedure.
     */
    abstract public String toAssembly ();
    // =============================================================================================================================



    // =============================================================================================================================
    /**
     * Generate assembly for any statically allocated elements in this procedure.
     *
     * @return the statics-segment assembly for this procedure.
     */
    abstract public String toStatics ();
    // =============================================================================================================================
    


// =================================================================================================================================
} // Procedure ()
// =================================================================================================================================

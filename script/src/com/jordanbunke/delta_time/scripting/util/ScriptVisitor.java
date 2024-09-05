package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ScriptParser;
import com.jordanbunke.delta_time.scripting.ScriptParserBaseVisitor;
import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.GenericIllegalNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.collection_init.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.function.FuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.function.HOFuncCallNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.function.HOFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.color_def.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.img_gen.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.min_max.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.rng.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.tex_lookup.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.scoped.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.operation.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.assignment.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.declaration.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.function.FuncExecuteNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.native_calls.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.*;

import java.util.List;
import java.util.function.Supplier;

public class ScriptVisitor
        extends ScriptParserBaseVisitor<ASTNode> {
    protected static final String SCOPE_SEP = ".";

    // function IDs
    protected static final String
            // properties
            RED_L = "red", RED_S = "r",
            GREEN_L = "green", GREEN_S = "g",
            BLUE_L = "blue", BLUE_S = "b",
            ALPHA_L = "alpha", ALPHA_S = "a",
            WIDTH_L = "width", WIDTH_S = "w",
            HEIGHT_L = "height", HEIGHT_S = "h",
            // scoped
            CALL = "call",
            HAS = "has", LOOKUP = "lookup", KEYS = "keys",
            SECTION = "section", PIXEL = "pixel",
            AT = "at", SUB = "sub",
            ADD = "add", REMOVE = "remove",
            DEFINE = "define",
            DRAW = "draw", DOT = "dot", LINE = "line", FILL = "fill",
            // global
            ABS = "abs", MIN = "min", MAX = "max", CLAMP = "clamp",
            RAND = "rand", PROB = "prob", FLIP_COIN = "flip_coin",
            FROM = "from", BLANK = "blank",
            TEX_COL_REPL = "tex_col_repl", GEN_LOOKUP = "gen_lookup",
            RGB = "rgb", RGBA = "rgba";

    // to extend
    @Override
    public ASTNode visitExtensionType(
            ScriptParser.ExtensionTypeContext ctx
    ) {
        return new GenericIllegalNode(
                TextPosition.fromToken(ctx.start),
                "Extension type");
    }

    @Override
    public ASTNode visitExtFuncCallStatement(
            final ScriptParser.ExtFuncCallStatementContext ctx) {
        return new GenericIllegalNode(
                TextPosition.fromToken(ctx.start),
                "Global extension function call statement");
    }

    @Override
    public ASTNode visitExtFuncCallExpression(
            final ScriptParser.ExtFuncCallExpressionContext ctx) {
        return new GenericIllegalNode(
                TextPosition.fromToken(ctx.start),
                "Global extension function call expression");
    }

    @Override
    public ASTNode visitExtPropertyExpression(
            final ScriptParser.ExtPropertyExpressionContext ctx) {
        return new GenericIllegalNode(TextPosition.fromToken(ctx.start),
                "Global extension property expression");
    }

    public ExpressionNode determineExtPropertyExpression(
            final TextPosition position, final ExpressionNode scope,
            final String propertyID
    ) {
        return new IllegalExpressionNode(position,
                "Undefined property \"" + propertyID +
                        "\" called on \"" + scope + "\"");
    }

    @SuppressWarnings("unused")
    public ExpressionNode determineExtScopedFunctionExpression(
            final TextPosition position, final ExpressionNode scope,
            final String functionID, final ExpressionNode... args
    ) {
        return new IllegalExpressionNode(position,
                "Undefined scoped function \"" + functionID +
                        "\" called on \"" + scope + "\"");
    }

    @SuppressWarnings("unused")
    public StatementNode determineExtScopedFunctionStatement(
            final TextPosition position, final ExpressionNode scope,
            final String functionID, final ExpressionNode... args
    ) {
        return new IllegalStatementNode(position,
                "Undefined scoped function \"" + functionID +
                        "\" called on \"" + scope + "\"");
    }

    // concrete implementations
    @Override
    public HeadFuncNode visitHead_rule(
            final ScriptParser.Head_ruleContext ctx
    ) {
        return new HeadFuncNode(
                TextPosition.fromToken(ctx.start),
                (FuncSignatureNode) visit(ctx.signature()),
                (StatementNode) visit(ctx.func_body()),
                ctx.helper().stream().map(this::visitHelper)
                        .toArray(HelperFuncNode[]::new));
    }

    @Override
    public HelperFuncNode visitHelper(
            final ScriptParser.HelperContext ctx
    ) {
        return new HelperFuncNode(
                TextPosition.fromToken(ctx.start),
                (FuncSignatureNode) visit(ctx.signature()),
                (StatementNode) visit(ctx.func_body()),
                ctx.ident().getText());
    }

    @Override
    public StatementNode visitStandardFuncBody(
            final ScriptParser.StandardFuncBodyContext ctx
    ) {
        return (StatementNode) visit(ctx.body());
    }

    @Override
    public ReturnStatementNode visitFunctionalFuncBody(
            final ScriptParser.FunctionalFuncBodyContext ctx
    ) {
        return ReturnStatementNode.forTyped(
                TextPosition.fromToken(ctx.start),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public FuncSignatureNode visitVoidReturnSignature(
            final ScriptParser.VoidReturnSignatureContext ctx
    ) {
        final boolean hasParams = ctx.param_list() != null;

        final ParametersNode parameters = hasParams
                ? visitParam_list(ctx.param_list())
                : new ParametersNode(
                        TextPosition.fromToken(ctx.RPAREN().getSymbol()),
                new ExplicitDeclarationNode[] {});

        return new FuncSignatureNode(
                TextPosition.fromToken(ctx.LPAREN().getSymbol()), parameters);
    }

    @Override
    public FuncSignatureNode visitTypeReturnSignature(
            final ScriptParser.TypeReturnSignatureContext ctx
    ) {
        final boolean hasParams = ctx.param_list() != null;

        final ParametersNode parameters = hasParams
                ? visitParam_list(ctx.param_list())
                : new ParametersNode(
                TextPosition.fromToken(ctx.RPAREN().getSymbol()),
                new ExplicitDeclarationNode[] {});

        return new FuncSignatureNode(
                TextPosition.fromToken(ctx.LPAREN().getSymbol()),
                parameters, (TypeNode) visit(ctx.type()));
    }

    @Override
    public ParametersNode visitParam_list(
            final ScriptParser.Param_listContext ctx
    ) {
        return new ParametersNode(
                TextPosition.fromToken(ctx.start),
                ctx.declaration().stream()
                        .map(this::visitDeclaration)
                        .toArray(ExplicitDeclarationNode[]::new));
    }

    @Override
    public ExplicitDeclarationNode visitDeclaration(
            final ScriptParser.DeclarationContext ctx
    ) {
        return new ExplicitDeclarationNode(
                TextPosition.fromToken(ctx.start),
                ctx.FINAL() == null,
                (TypeNode) visit(ctx.type()),
                visitIdent(ctx.ident()));
    }

    @Override
    public IdentifierNode visitIdent(
            final ScriptParser.IdentContext ctx
    ) {
        return new IdentifierNode(
                TextPosition.fromToken(ctx.start),
                ctx.IDENTIFIER().getSymbol().getText());
    }

    @Override
    public BaseTypeNode visitBoolType(
            final ScriptParser.BoolTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.BOOL);
    }

    @Override
    public BaseTypeNode visitIntType(
            final ScriptParser.IntTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.INT);
    }

    @Override
    public BaseTypeNode visitFloatType(
            final ScriptParser.FloatTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.FLOAT);
    }

    @Override
    public BaseTypeNode visitCharType(
            final ScriptParser.CharTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.CHAR);
    }

    @Override
    public BaseTypeNode visitStringType(
            final ScriptParser.StringTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.STRING);
    }

    @Override
    public BaseTypeNode visitImageType(
            final ScriptParser.ImageTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.IMAGE);
    }

    @Override
    public BaseTypeNode visitColorType(
            final ScriptParser.ColorTypeContext ctx
    ) {
        return new BaseTypeNode(TextPosition.fromToken(ctx.start),
                BaseTypeNode.Type.COLOR);
    }

    @Override
    public CollectionTypeNode visitArrayType(
            final ScriptParser.ArrayTypeContext ctx
    ) {
        return new CollectionTypeNode(
                TextPosition.fromToken(ctx.start),
                CollectionTypeNode.Type.ARRAY,
                (TypeNode) visit(ctx.type()));
    }

    @Override
    public CollectionTypeNode visitSetType(
            final ScriptParser.SetTypeContext ctx
    ) {
        return new CollectionTypeNode(
                TextPosition.fromToken(ctx.start),
                CollectionTypeNode.Type.SET,
                (TypeNode) visit(ctx.type()));
    }

    @Override
    public CollectionTypeNode visitListType(
            final ScriptParser.ListTypeContext ctx
    ) {
        return new CollectionTypeNode(
                TextPosition.fromToken(ctx.start),
                CollectionTypeNode.Type.LIST,
                (TypeNode) visit(ctx.type()));
    }

    @Override
    public MapTypeNode visitMapType(
            final ScriptParser.MapTypeContext ctx
    ) {
        return new MapTypeNode(
                TextPosition.fromToken(ctx.start),
                (TypeNode) visit(ctx.key),
                (TypeNode) visit(ctx.val));
    }

    @Override
    public FuncTypeNode visitFunctionType(
            final ScriptParser.FunctionTypeContext ctx
    ) {
        final TypeNode[] paramTypes = ctx.func_type().param_types().type()
                .stream().map(p -> (TypeNode) visit(p))
                .toArray(TypeNode[]::new);

        return new FuncTypeNode(TextPosition.fromToken(ctx.start),
                paramTypes, (TypeNode) visit(ctx.func_type().ret));
    }

    @Override
    public StatementNode visitLoopStatement(
            final ScriptParser.LoopStatementContext ctx
    ) {
        return (StatementNode) visit(ctx.loop_stat());
    }

    @Override
    public WhileLoopNode visitWhileLoop(
            final ScriptParser.WhileLoopContext ctx
    ) {
        final ExpressionNode loopCondition =
                (ExpressionNode) visit(ctx.while_def().expr());
        final StatementNode loopBody = (StatementNode) visit(ctx.body());

        return new WhileLoopNode(
                TextPosition.fromToken(ctx.start),
                loopCondition, loopBody);
    }

    @Override
    public DoWhileLoopNode visitDoWhileLoop(
            final ScriptParser.DoWhileLoopContext ctx
    ) {
        final ExpressionNode loopCondition =
                (ExpressionNode) visit(ctx.while_def().expr());
        final StatementNode loopBody = (StatementNode) visit(ctx.body());

        return new DoWhileLoopNode(
                TextPosition.fromToken(ctx.start),
                loopCondition, loopBody);
    }

    @Override
    public IteratorLoopNode visitIteratorLoop(
            final ScriptParser.IteratorLoopContext ctx
    ) {
        final DeclarationNode declaration =
                (DeclarationNode) visit(ctx.iteration_def().iterator_declaration());
        final ExpressionNode collection =
                (ExpressionNode) visit(ctx.iteration_def().expr());
        final StatementNode loopBody = (StatementNode) visit(ctx.body());

        return new IteratorLoopNode(
                TextPosition.fromToken(ctx.start),
                declaration, collection, loopBody);
    }

    @Override
    public ExplicitDeclarationNode visitExplicitDeclaration(
            final ScriptParser.ExplicitDeclarationContext ctx
    ) {
        return visitDeclaration(ctx.declaration());
    }

    @Override
    public ImplicitDeclarationNode visitImplicitDeclaration(
            final ScriptParser.ImplicitDeclarationContext ctx
    ) {
        return new ImplicitDeclarationNode(
                TextPosition.fromToken(ctx.ident().start),
                visitIdent(ctx.ident()));
    }

    @Override
    public ForLoopNode visitForLoop(
            final ScriptParser.ForLoopContext ctx
    ) {
        final InitializationNode initialization =
                visitVar_init(ctx.for_def().var_init());
        final ExpressionNode loopCondition =
                (ExpressionNode) visit(ctx.for_def().expr());
        final AssignmentNode incrementation =
                (AssignmentNode) visit(ctx.for_def().assignment());
        final StatementNode loopBody = (StatementNode) visit(ctx.body());

        return new ForLoopNode(TextPosition.fromToken(ctx.start),
                initialization, loopCondition, incrementation, loopBody);
    }

    @Override
    public IfStatementNode visitIfStatement(
            final ScriptParser.IfStatementContext ctx
    ) {
        return visitIf_stat(ctx.if_stat());
    }

    @Override
    public IfStatementNode visitIf_stat(
            final ScriptParser.If_statContext ctx
    ) {
        final ExpressionNode[] conditions = ctx.if_def().stream()
                .map(i -> (ExpressionNode) visit(i.cond))
                .toArray(ExpressionNode[]::new);
        final StatementNode[] bodies = ctx.if_def().stream()
                .map(i -> (StatementNode) visit(i.body()))
                .toArray(StatementNode[]::new);
        final StatementNode elseBody = ctx.elseBody != null
                ? (StatementNode) visit(ctx.elseBody) : null;

        return new IfStatementNode(TextPosition.fromToken(ctx.start),
                conditions, bodies, elseBody);
    }

    @Override
    public ExplicitDeclarationNode visitVarDefStatement(
            final ScriptParser.VarDefStatementContext ctx
    ) {
        return (ExplicitDeclarationNode) visit(ctx.var_def());
    }

    @Override
    public InitializationNode visitVar_init(
            final ScriptParser.Var_initContext ctx
    ) {
        return new InitializationNode(
                TextPosition.fromToken(ctx.start),
                ctx.declaration().FINAL() == null,
                (TypeNode) visit(ctx.declaration().type()),
                visitIdent(ctx.declaration().ident()),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public AssignmentNode visitAssignmentStatement(
            final ScriptParser.AssignmentStatementContext ctx
    ) {
        return (AssignmentNode) visit(ctx.assignment());
    }

    @Override
    public ReturnStatementNode visitReturnStatement(
            final ScriptParser.ReturnStatementContext ctx
    ) {
        final TextPosition position =
                TextPosition.fromToken(ctx.return_stat().start);

        if (ctx.return_stat().expr() == null)
            return ReturnStatementNode.forVoid(position);

        return ReturnStatementNode.forTyped(position,
                (ExpressionNode) visit(ctx.return_stat().expr()));
    }

    @Override
    public StatementNode visitScopedFuncCallStatement(
            final ScriptParser.ScopedFuncCallStatementContext ctx
    ) {
        final ExpressionNode[] args = unpackElements(ctx.args().elements());
        final ExpressionNode scope = (ExpressionNode) visit(ctx.expr());
        // trim leading "." from the function ID
        final String functionID = ctx.subident().SUB_IDENT()
                .getSymbol().getText().substring(SCOPE_SEP.length());
        final TextPosition position = TextPosition.fromToken(ctx.start);

        final Supplier<StatementNode> extension =
                () -> determineExtScopedFunctionStatement(
                        position, scope, functionID, args);

        return switch (functionID) {
            case ADD -> switch (args.length) {
                case 1 -> new AddNode(position, scope, args[0], null);
                case 2 -> new AddNode(position, scope, args[0], args[1]);
                default -> extension.get();
            };
            case REMOVE -> args.length == 1
                    ? new RemoveNode(position, scope, args[0])
                    : extension.get();
            case DEFINE -> args.length == 2
                    ? new MapDefineNode(position, scope, args[0], args[1])
                    : extension.get();
            case DRAW -> args.length == 3
                    ? new DrawNode(position, scope, args[0], args[1], args[2])
                    : extension.get();
            case DOT -> args.length == 3
                    ? new DotNode(position, scope, args[0], args[1], args[2])
                    : extension.get();
            case LINE -> args.length == 6
                    ? new DrawLineNode(position, scope,
                    args[0], args[1], args[2], args[3], args[4], args[5])
                    : extension.get();
            case FILL -> args.length == 5
                    ? new FillNode(position, scope,
                    args[0], args[1], args[2], args[3], args[4])
                    : extension.get();
            default -> extension.get();
        };
    }

    @Override
    public StatementNode visitFunctionCallStatement(
            final ScriptParser.FunctionCallStatementContext ctx
    ) {
        final ExpressionNode[] args = unpackElements(ctx.args().elements());
        final String functionID = ctx.ident().getText();
        final TextPosition position = TextPosition.fromToken(ctx.start);

        return new FuncExecuteNode(position, functionID, args);
    }

    @Override
    public StatementNode visitSingleStatBody(
            final ScriptParser.SingleStatBodyContext ctx
    ) {
        return (StatementNode) visit(ctx.stat());
    }

    @Override
    public BodyStatementNode visitComplexBody(
            final ScriptParser.ComplexBodyContext ctx
    ) {
        return new BodyStatementNode(
                TextPosition.fromToken(ctx.LCURLY().getSymbol()),
                ctx.stat().stream()
                        .map(s -> (StatementNode) visit(s))
                        .toArray(StatementNode[]::new));
    }

    @Override
    public StandardAssignmentNode visitStandardAssignment(
            final ScriptParser.StandardAssignmentContext ctx
    ) {
        return new StandardAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public NoOperandAssignmentNode visitIncrementAssignment(
            final ScriptParser.IncrementAssignmentContext ctx
    ) {
        return new NoOperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                true);
    }

    @Override
    public NoOperandAssignmentNode visitDecrementAssignment(
            final ScriptParser.DecrementAssignmentContext ctx
    ) {
        return new NoOperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                false);
    }

    @Override
    public OperandAssignmentNode visitAddAssignment(
            final ScriptParser.AddAssignmentContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.ADD,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public OperandAssignmentNode visitSubAssignment(
            final ScriptParser.SubAssignmentContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.SUBTRACT,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public OperandAssignmentNode visitMultAssignment(
            final ScriptParser.MultAssignmentContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.MULTIPLY,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public OperandAssignmentNode visitDivAssignmnet(
            final ScriptParser.DivAssignmnetContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.DIVIDE,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public OperandAssignmentNode visitModAssignment(
            final ScriptParser.ModAssignmentContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.MODULO,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public OperandAssignmentNode visitAndAssignment(
            final ScriptParser.AndAssignmentContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.AND,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public OperandAssignmentNode visitOrAssignment(
            final ScriptParser.OrAssignmentContext ctx
    ) {
        return new OperandAssignmentNode(
                TextPosition.fromToken(ctx.start),
                (AssignableNode) visit(ctx.assignable()),
                OperandAssignmentNode.Operator.OR,
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public BoolLiteralNode visitBoolLiteral(
            final ScriptParser.BoolLiteralContext ctx
    ) {
        return (BoolLiteralNode) visit(ctx.bool_lit());
    }

    @Override
    public BoolLiteralNode visitTrue(final ScriptParser.TrueContext ctx) {
        return new BoolLiteralNode(
                TextPosition.fromToken(ctx.TRUE().getSymbol()), true);
    }

    @Override
    public BoolLiteralNode visitFalse(final ScriptParser.FalseContext ctx) {
        return new BoolLiteralNode(
                TextPosition.fromToken(ctx.FALSE().getSymbol()), false);
    }

    @Override
    public FloatLiteralNode visitFloatLiteral(
            final ScriptParser.FloatLiteralContext ctx
    ) {
        return new FloatLiteralNode(
                TextPosition.fromToken(ctx.FLOAT_LIT().getSymbol()),
                Float.parseFloat(ctx.FLOAT_LIT().getSymbol().getText()));
    }

    @Override
    public IntLiteralNode visitIntLiteral(
            final ScriptParser.IntLiteralContext ctx
    ) {
        return (IntLiteralNode) visit(ctx.int_lit());
    }

    @Override
    public IntLiteralNode visitHexadecimal(
            final ScriptParser.HexadecimalContext ctx
    ) {
        return new IntLiteralNode(
                TextPosition.fromToken(ctx.start),
                Integer.parseInt(ctx.HEX_LIT().getText()
                        .substring(2).toUpperCase(), 16));
    }

    @Override
    public IntLiteralNode visitDecimal(
            final ScriptParser.DecimalContext ctx
    ) {
        return new IntLiteralNode(
                TextPosition.fromToken(ctx.start),
                Integer.parseInt(ctx.DEC_LIT().getText()));
    }

    @Override
    public ColorHexCodeLiteralNode visitColorLiteral(
            final ScriptParser.ColorLiteralContext ctx
    ) {
        return new ColorHexCodeLiteralNode(
                TextPosition.fromToken(ctx.start),
                ctx.getText());
    }

    @Override
    public CharLiteralNode visitCharLiteral(
            final ScriptParser.CharLiteralContext ctx
    ) {
        return new CharLiteralNode(
                TextPosition.fromToken(ctx.CHAR_LIT().getSymbol()),
                ctx.CHAR_LIT().getSymbol().getText().charAt(1));
    }

    @Override
    public StringLiteralNode visitStringLiteral(
            final ScriptParser.StringLiteralContext ctx
    ) {
        final String withQuotes = ctx.STRING_LIT().getSymbol().getText();

        return new StringLiteralNode(
                TextPosition.fromToken(ctx.STRING_LIT().getSymbol()),
                withQuotes.substring(1, withQuotes.length() - 1));
    }

    @Override
    public ExpressionNode visitNestedExpression(
            final ScriptParser.NestedExpressionContext ctx
    ) {
        return (ExpressionNode) visit(ctx.expr());
    }

    @Override
    public CastNode visitCastExpression(
            final ScriptParser.CastExpressionContext ctx
    ) {
        return new CastNode(TextPosition.fromToken(ctx.start),
                (TypeNode) visit(ctx.type()),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public ExpressionNode visitFunctionCallExpression(
            final ScriptParser.FunctionCallExpressionContext ctx
    ) {
        final ExpressionNode[] args = unpackElements(ctx.args().elements());
        final String functionID = ctx.ident().getText();
        final TextPosition position = TextPosition.fromToken(ctx.start);

        final Supplier<ExpressionNode> scriptDefined =
                () -> new FuncCallNode(position, functionID, args);

        return switch (functionID) {
            case ABS -> args.length == 1
                    ? new AbsoluteNode(position, args[0])
                    : scriptDefined.get();
            case CLAMP -> args.length == 3
                    ? new ClampNode(position, args[0], args[1], args[2])
                    : scriptDefined.get();
            case PROB -> args.length == 1
                    ? new ProbabilityNode(position, args[0])
                    : scriptDefined.get();
            case FROM -> args.length == 1
                    ? new ImageFromPathNode(position, args[0])
                    : scriptDefined.get();
            case BLANK -> args.length == 2
                    ? new ImageOfBoundsNode(position, args[0], args[1])
                    : scriptDefined.get();
            case TEX_COL_REPL -> args.length == 3
                    ? new TextureColorReplaceNode(
                            position, args[0], args[1], args[2])
                    : scriptDefined.get();
            case GEN_LOOKUP -> args.length == 2
                    ? new GenLookupNode(position, args[0], args[1])
                    : scriptDefined.get();
            case RGB -> args.length == 3
                    ? new RGBColorNode(position, args[0], args[1], args[2])
                    : scriptDefined.get();
            case RGBA -> args.length == 4
                    ? new RGBAColorNode(
                            position, args[0], args[1], args[2], args[3])
                    : scriptDefined.get();
            case MIN -> switch (args.length) {
                case 1 -> new MinMaxCollectionNode(position, false, args[0]);
                case 2 -> new MinMaxTwoArgNode(position, false, args[0], args[1]);
                default -> scriptDefined.get();
            };
            case MAX -> switch (args.length) {
                case 1 -> new MinMaxCollectionNode(position, true, args[0]);
                case 2 -> new MinMaxTwoArgNode(position, true, args[0], args[1]);
                default -> scriptDefined.get();
            };
            case RAND -> switch (args.length) {
                case 0 -> new RandNode(position);
                case 2 -> new RandTwoArgNode(position, args[0], args[1]);
                default -> scriptDefined.get();
            };
            case FLIP_COIN -> switch (args.length) {
                case 0 -> new FlipCoinNode(position);
                case 2 -> new TernaryOperationNode(position,
                        new FlipCoinNode(position), args[0], args[1]);
                default -> scriptDefined.get();
            };
            default -> scriptDefined.get();
        };
    }

    @Override
    public ExpressionNode visitPropertyExpression(
            final ScriptParser.PropertyExpressionContext ctx
    ) {
        final ExpressionNode scope = (ExpressionNode) visit(ctx.expr());
        // trim leading "." from the property ID
        final String propertyID = ctx.subident().SUB_IDENT()
                .getSymbol().getText().substring(SCOPE_SEP.length());
        final TextPosition position = TextPosition.fromToken(ctx.start);

        return switch (propertyID) {
            case RED_L, RED_S -> new ColorChannelNode(
                    position, scope, ColorChannelNode.Channel.RED);
            case GREEN_L, GREEN_S -> new ColorChannelNode(
                    position, scope, ColorChannelNode.Channel.GREEN);
            case BLUE_L, BLUE_S -> new ColorChannelNode(
                    position, scope, ColorChannelNode.Channel.BLUE);
            case ALPHA_L, ALPHA_S -> new ColorChannelNode(
                    position, scope, ColorChannelNode.Channel.ALPHA);
            case WIDTH_L, WIDTH_S -> new ImageBoundNode(position, scope, true);
            case HEIGHT_L, HEIGHT_S -> new ImageBoundNode(position, scope, false);
            default -> determineExtPropertyExpression(position, scope, propertyID);
        };
    }

    @Override
    public ExpressionNode visitScopedFuncCallExpression(
            final ScriptParser.ScopedFuncCallExpressionContext ctx
    ) {
        final ExpressionNode[] args = unpackElements(ctx.args().elements());
        final ExpressionNode scope = (ExpressionNode) visit(ctx.expr());
        // trim leading "." from the function ID
        final String functionID = ctx.subident().SUB_IDENT()
                .getSymbol().getText().substring(SCOPE_SEP.length());
        final TextPosition position = TextPosition.fromToken(ctx.start);

        final Supplier<ExpressionNode> extension =
                () -> determineExtScopedFunctionExpression(
                        position, scope, functionID, args);

        return switch (functionID) {
            case CALL -> new HOFuncCallNode(position, scope, args);
            case HAS -> args.length == 1
                    ? new ContainsNode(position, scope, args[0])
                    : extension.get();
            case LOOKUP -> args.length == 1
                    ? new MapLookupNode(position, scope, args[0])
                    : extension.get();
            case KEYS -> args.length == 0
                    ? new MapKeysetNode(position, scope)
                    : extension.get();
            case SECTION -> args.length == 4
                    ? new ImageSectionNode(position, scope, args[0],
                        args[1], args[2], args[3])
                    : extension.get();
            case PIXEL -> args.length == 2
                    ? new ColorAtPixelNode(position, scope, args[0], args[1])
                    : extension.get();
            case AT -> args.length == 1
                    ? new CharAtNode(position, scope, args[0])
                    : extension.get();
            case SUB -> args.length == 2
                    ? new SubstringNode(position, scope, args[0], args[1])
                    : extension.get();
            default -> extension.get();
        };
    }

    @Override
    public HOFuncNode visitHOFuncExpression(
            final ScriptParser.HOFuncExpressionContext ctx
    ) {
        return new HOFuncNode(TextPosition.fromToken(ctx.start),
                ctx.ident().getText());
    }

    @Override
    public AssignableNode visitAssignableExpression(
            final ScriptParser.AssignableExpressionContext ctx
    ) {
        return (AssignableNode) visit(ctx.assignable());
    }

    @Override
    public IdentifierNode visitSimpleAssignable(
            final ScriptParser.SimpleAssignableContext ctx
    ) {
        return visitIdent(ctx.ident());
    }

    @Override
    public ListAssignableNode visitListAssignable(
            final ScriptParser.ListAssignableContext ctx
    ) {
        return new ListAssignableNode(
                TextPosition.fromToken(ctx.start),
                ctx.ident().IDENTIFIER().getSymbol().getText(),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public ArrayAssignableNode visitArrayAssignable(
            final ScriptParser.ArrayAssignableContext ctx
    ) {
        return new ArrayAssignableNode(
                TextPosition.fromToken(ctx.start),
                ctx.ident().IDENTIFIER().getSymbol().getText(),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public LiteralNode visitLiteralExpression(
            final ScriptParser.LiteralExpressionContext ctx
    ) {
        return (LiteralNode) visit(ctx.literal());
    }

    @Override
    public UnaryOperationNode visitUnaryExpression(
            final ScriptParser.UnaryExpressionContext ctx
    ) {
        return new UnaryOperationNode(TextPosition.fromToken(ctx.op),
                ctx.op.getText(), (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public BinaryOperationNode visitArithmeticBinExpression(
            final ScriptParser.ArithmeticBinExpressionContext ctx
    ) {
        return new BinaryOperationNode(
                TextPosition.fromToken(ctx.a.start), ctx.op.getText(),
                (ExpressionNode) visit(ctx.a),
                (ExpressionNode) visit(ctx.b));
    }

    @Override
    public BinaryOperationNode visitMultBinExpression(
            final ScriptParser.MultBinExpressionContext ctx
    ) {
        return new BinaryOperationNode(
                TextPosition.fromToken(ctx.a.start), ctx.op.getText(),
                (ExpressionNode) visit(ctx.a),
                (ExpressionNode) visit(ctx.b));
    }

    @Override
    public BinaryOperationNode visitPowerBinExpression(
            final ScriptParser.PowerBinExpressionContext ctx
    ) {
        return new BinaryOperationNode(
                TextPosition.fromToken(ctx.a.start),
                ctx.RAISE().getSymbol().getText(),
                (ExpressionNode) visit(ctx.a),
                (ExpressionNode) visit(ctx.b));
    }

    @Override
    public BinaryOperationNode visitComparisonBinExpression(
            final ScriptParser.ComparisonBinExpressionContext ctx
    ) {
        return new BinaryOperationNode(
                TextPosition.fromToken(ctx.a.start), ctx.op.getText(),
                (ExpressionNode) visit(ctx.a),
                (ExpressionNode) visit(ctx.b));
    }

    @Override
    public BinaryOperationNode visitLogicBinExpression(
            final ScriptParser.LogicBinExpressionContext ctx
    ) {
        return new BinaryOperationNode(
                TextPosition.fromToken(ctx.a.start), ctx.op.getText(),
                (ExpressionNode) visit(ctx.a),
                (ExpressionNode) visit(ctx.b));
    }

    @Override
    public TernaryOperationNode visitTernaryExpression(
            final ScriptParser.TernaryExpressionContext ctx
    ) {
        return new TernaryOperationNode(
                TextPosition.fromToken(ctx.start),
                (ExpressionNode) visit(ctx.cond),
                (ExpressionNode) visit(ctx.if_),
                (ExpressionNode) visit(ctx.else_));
    }

    @Override
    public ExplicitMapInitNode visitExplicitMapExpression(
            final ScriptParser.ExplicitMapExpressionContext ctx
    ) {
        final List<ScriptParser.K_v_pairContext> keyValPairs =
                ctx.k_v_pairs().k_v_pair();
        final int n = keyValPairs.size();

        final ExpressionNode[] keys = new ExpressionNode[n];
        final ExpressionNode[] vals = new ExpressionNode[n];

        for (int i = 0; i < n; i++) {
            keys[i] = (ExpressionNode) visit(keyValPairs.get(i).key);
            vals[i] = (ExpressionNode) visit(keyValPairs.get(i).val);
        }

        return new ExplicitMapInitNode(
                TextPosition.fromToken(ctx.LCURLY().getSymbol()),
                keys, vals);
    }

    @Override
    public ExplicitCollectionInitNode visitExplicitArrayExpression(
            final ScriptParser.ExplicitArrayExpressionContext ctx
    ) {
        return new ExplicitCollectionInitNode(
                TextPosition.fromToken(ctx.LBRACKET().getSymbol()),
                CollectionTypeNode.Type.ARRAY,
                unpackElements(ctx.elements()));
    }

    @Override
    public ExplicitCollectionInitNode visitExplicitListExpression(
            final ScriptParser.ExplicitListExpressionContext ctx
    ) {
        return new ExplicitCollectionInitNode(
                TextPosition.fromToken(ctx.LT().getSymbol()),
                CollectionTypeNode.Type.LIST,
                unpackElements(ctx.elements()));
    }

    @Override
    public ExplicitCollectionInitNode visitExplicitSetExpression(
            final ScriptParser.ExplicitSetExpressionContext ctx
    ) {
        return new ExplicitCollectionInitNode(
                TextPosition.fromToken(ctx.LCURLY().getSymbol()),
                CollectionTypeNode.Type.SET,
                unpackElements(ctx.elements()));
    }

    private ExpressionNode[] unpackElements(
            final ScriptParser.ElementsContext ctx
    ) {
        if (ctx == null)
            return new ExpressionNode[0];

        return ctx.expr().stream()
                .map(e -> (ExpressionNode) visit(e))
                .toArray(ExpressionNode[]::new);
    }

    @Override
    public NewArrayNode visitNewArrayExpression(
            final ScriptParser.NewArrayExpressionContext ctx
    ) {
        return new NewArrayNode(
                TextPosition.fromToken(ctx.NEW().getSymbol()),
                (TypeNode) visit(ctx.type()),
                (ExpressionNode) visit(ctx.expr()));
    }

    @Override
    public NewMapNode visitNewMapExpression(
            final ScriptParser.NewMapExpressionContext ctx
    ) {
        return new NewMapNode(
                TextPosition.fromToken(ctx.NEW().getSymbol()),
                (TypeNode) visit(ctx.kt), (TypeNode) visit(ctx.vt));
    }
}

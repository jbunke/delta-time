package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.collection.*;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;

import java.awt.*;
import java.util.*;
import java.util.List;

public final class TypeCompatibility {
    private static final Map<TypeNode, Class<?>> BASE_TYPES;

    static {
        BASE_TYPES = new HashMap<>();

        BASE_TYPES.put(TypeNode.getInt(), Integer.class);
        BASE_TYPES.put(TypeNode.getBool(), Boolean.class);
        BASE_TYPES.put(TypeNode.getFloat(), Float.class);
        BASE_TYPES.put(TypeNode.getChar(), Character.class);
        BASE_TYPES.put(TypeNode.getImage(), GameImage.class);
        BASE_TYPES.put(TypeNode.getColor(), Color.class);
        BASE_TYPES.put(TypeNode.getString(), String.class);
    }

    public static void prepArgs(final Object... args) {
        for (int i = 0; i < args.length; i++)
            args[i] = prepArg(args[i]);
    }

    private static Object prepArg(final Object arg) {
        if (arg instanceof ScriptCollection || arg instanceof ScriptMap)
            return arg;

        if (arg instanceof List<?> l) {
            final ScriptList list = new ScriptList();
            l.forEach(elem -> list.add(prepArg(elem)));

            return list;
        } else if (arg instanceof Set<?> s) {
            final ScriptSet set = new ScriptSet();
            s.forEach(elem -> set.add(prepArg(elem)));

            return set;
        } else if (arg instanceof Object[] a) {
            final ScriptArray array = new ScriptArray(a.length);

            for (int i = 0; i < a.length; i++)
                array.set(i, prepArg(a[i]));

            return array;
        } else if (arg instanceof Map<?,?> m) {
            final ScriptMap map = new ScriptMap();

            for (Object key : m.keySet())
                map.put(prepArg(key), prepArg(m.get(key)));

            return map;
        }
        else if (arg instanceof boolean[] a)
            return prepArg(objectifyBoolArray(a));
        else if (arg instanceof double[] a)
            return prepArg(objectifyDoubleArray(a));
        else if (arg instanceof int[] a)
            return prepArg(objectifyIntArray(a));
        else if (arg instanceof char[] a)
            return prepArg(objectifyCharArray(a));
        else if (notABaseType(arg)) {
            ScriptErrorLog.runtimeError(TextPosition.N_A,
                    "Attempting to execute script with argument of an invalid data type: " +
                            arg.getClass().getSimpleName());
        }

        return arg;
    }

    public static <T> void addBaseType(
            final TypeNode typeNode, final Class<T> typeObjectClass
    ) {
        BASE_TYPES.put(typeNode, typeObjectClass);
    }

    public static TypeNode resolveType(final Class<?> objectClass) {
        if (objectClass == null)
            return null;

        for (TypeNode type : BASE_TYPES.keySet()) {
            final Class<?> typeClass = BASE_TYPES.get(type);

            if (typeClass.isAssignableFrom(objectClass))
                return type;
        }

        return null;
    }

    public static String resolveTypeName(final Class<?> objectClass) {
        final TypeNode type = resolveType(objectClass);

        if (type == null)
            return objectClass == null ? "null" : objectClass.getName();

        return type.toString();
    }

    private static boolean notABaseType(final Object arg) {
        for (Class<?> baseType : BASE_TYPES.values())
            if (baseType.isInstance(arg))
                return false;

        return true;
    }

    private static Boolean[] objectifyBoolArray(final boolean[] bs) {
        final Boolean[] os = new Boolean[bs.length];

        for (int i = 0; i < bs.length; i++)
            os[i] = bs[i];

        return os;
    }

    private static Integer[] objectifyIntArray(final int[] is) {
        final Integer[] os = new Integer[is.length];

        for (int i = 0; i < is.length; i++)
            os[i] = is[i];

        return os;
    }

    private static Double[] objectifyDoubleArray(final double[] ds) {
        final Double[] os = new Double[ds.length];

        for (int i = 0; i < ds.length; i++)
            os[i] = ds[i];

        return os;
    }

    private static Character[] objectifyCharArray(final char[] cs) {
        final Character[] os = new Character[cs.length];

        for (int i = 0; i < cs.length; i++)
            os[i] = cs[i];

        return os;
    }
}

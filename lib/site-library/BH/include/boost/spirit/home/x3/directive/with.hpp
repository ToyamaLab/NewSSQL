/*=============================================================================
    Copyright (c) 2014 Joel de Guzman

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
=============================================================================*/
#if !defined(SPIRIT_X3_WITH_MAY_02_2014_0749AM)
#define SPIRIT_X3_WITH_MAY_02_2014_0749AM

#include <boost/spirit/home/x3/support/unused.hpp>
#include <boost/spirit/home/x3/core/parser.hpp>

namespace boost { namespace spirit { namespace x3
{
    ///////////////////////////////////////////////////////////////////////////
    // with directive injects a value into the context prior to parsing.
    ///////////////////////////////////////////////////////////////////////////
    template <typename Subject, typename Derived, typename T>
    struct with_value_holder
      : unary_parser<Subject, Derived>
    {
        typedef unary_parser<Subject, Derived> base_type;
        mutable T val;
<<<<<<< HEAD
        with_value_holder(Subject const& subject, T const& val)
          : base_type(subject)
          , val(val) {}
    };
    
    template <typename Subject, typename Derived, typename T>
    struct with_value_holder<Subject, Derived, T const>
      : unary_parser<Subject, Derived>
    {
        typedef unary_parser<Subject, Derived> base_type;
        T val;
        with_value_holder(Subject const& subject, T const& val)
=======
        with_value_holder(Subject const& subject, T&& val)
          : base_type(subject)
          , val(std::forward<T>(val)) {}
    };
    
    template <typename Subject, typename Derived, typename T>
    struct with_value_holder<Subject, Derived, T&>
      : unary_parser<Subject, Derived>
    {
        typedef unary_parser<Subject, Derived> base_type;
        T& val;
        with_value_holder(Subject const& subject, T& val)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
          : base_type(subject)
          , val(val) {}
    };

    template <typename Subject, typename ID, typename T>
    struct with_directive
      : with_value_holder<Subject, with_directive<Subject, ID, T>, T>
    {
        typedef with_value_holder<Subject, with_directive<Subject, ID, T>, T> base_type;
        static bool const is_pass_through_unary = true;
        static bool const handles_container = Subject::handles_container;

        typedef Subject subject_type;

<<<<<<< HEAD
        with_directive(Subject const& subject, T const& val)
          : base_type(subject, val) {}
=======
        with_directive(Subject const& subject, T&& val)
          : base_type(subject, std::forward<T>(val)) {}
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        template <typename Iterator, typename Context
          , typename RContext, typename Attribute>
        bool parse(Iterator& first, Iterator const& last
          , Context const& context, RContext& rcontext, Attribute& attr) const
        {
            return this->subject.parse(
                first, last
              , make_context<ID>(this->val, context)
              , rcontext
              , attr);
        }
    };
   
<<<<<<< HEAD
    template <typename ID, typename T, typename NextContext = unused_type>
    struct with_context
    {
        typedef context<ID, T, NextContext> type;
    };
    
    template <typename ID, typename T>
    struct with_context<ID, T, unused_type>
    {
        typedef context<ID, T> type;
    };

    template <typename ID, typename T>
    struct with_gen
    {
        T& val;

        with_gen(T& val)
          : val(val) {}
=======
    template <typename ID, typename T>
    struct with_gen
    {
        T&& val;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        template <typename Subject>
        with_directive<typename extension::as_parser<Subject>::value_type, ID, T>
        operator[](Subject const& subject) const
        {
<<<<<<< HEAD
            return { as_parser(subject), val };
=======
            return { as_parser(subject), std::forward<T>(val) };
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
    };

    template <typename ID, typename T>
<<<<<<< HEAD
    inline with_gen<ID, T> with(T& val)
    {
        return { val };
    }
    
    template <typename ID, typename T>
    inline with_gen<ID, T const> with(T const& val)
    {
        return { val };
=======
    inline with_gen<ID, T> with(T&& val)
    {
        return { std::forward<T>(val) };
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
}}}

#endif

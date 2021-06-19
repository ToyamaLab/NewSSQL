<<<<<<< HEAD
/*=============================================================================
    Copyright (c) 2007 Tobias Schwinger
  
    Use modification and distribution are subject to the Boost Software 
    License, Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
    http://www.boost.org/LICENSE_1_0.txt).
==============================================================================*/

#ifndef BOOST_FUNCTIONAL_VALUE_FACTORY_HPP_INCLUDED
#   ifndef BOOST_PP_IS_ITERATING

#     include <boost/preprocessor/iteration/iterate.hpp>
#     include <boost/preprocessor/repetition/enum_params.hpp>
#     include <boost/preprocessor/repetition/enum_binary_params.hpp>

#     include <new>
#     include <boost/pointee.hpp>
#     include <boost/get_pointer.hpp>
#     include <boost/non_type.hpp>
#     include <boost/type_traits/remove_cv.hpp>

#     ifndef BOOST_FUNCTIONAL_VALUE_FACTORY_MAX_ARITY
#       define BOOST_FUNCTIONAL_VALUE_FACTORY_MAX_ARITY 10
#     elif BOOST_FUNCTIONAL_VALUE_FACTORY_MAX_ARITY < 3
#       undef  BOOST_FUNCTIONAL_VALUE_FACTORY_MAX_ARITY
#       define BOOST_FUNCTIONAL_VALUE_FACTORY_MAX_ARITY 3
#     endif

namespace boost
{
    template< typename T >
    class value_factory;

    //----- ---- --- -- - -  -   -

    template< typename T >
    class value_factory
    {
      public:
        typedef T result_type;

        value_factory()
        { }

#     define BOOST_PP_FILENAME_1 <boost/functional/value_factory.hpp>
#     define BOOST_PP_ITERATION_LIMITS (0,BOOST_FUNCTIONAL_VALUE_FACTORY_MAX_ARITY)
#     include BOOST_PP_ITERATE()
    };

    template< typename T > class value_factory<T&>;
    // forbidden, would create a dangling reference
}
#     define BOOST_FUNCTIONAL_VALUE_FACTORY_HPP_INCLUDED
#   else // defined(BOOST_PP_IS_ITERATING)

#     define N BOOST_PP_ITERATION()
#     if N > 0
    template< BOOST_PP_ENUM_PARAMS(N, typename T) >
#     endif
    inline result_type operator()(BOOST_PP_ENUM_BINARY_PARAMS(N,T,& a)) const
    {
        return result_type(BOOST_PP_ENUM_PARAMS(N,a));
    }
#     undef N

#   endif // defined(BOOST_PP_IS_ITERATING)

#endif // include guard

=======
/*
Copyright 2007 Tobias Schwinger

Copyright 2019 Glen Joseph Fernandes
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)
*/
#ifndef BOOST_FUNCTIONAL_VALUE_FACTORY_HPP
#define BOOST_FUNCTIONAL_VALUE_FACTORY_HPP

#include <boost/config.hpp>
#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
#include <utility>
#endif

namespace boost {

template<class  T>
class value_factory;

template<class T>
class value_factory {
public:
    typedef T result_type;

#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
    template<class... Args>
    result_type operator()(Args&&... args) const {
        return result_type(std::forward<Args>(args)...);
    }
#else
    result_type operator()() const {
        return result_type();
    }

    template<class A0>
    result_type operator()(A0& a0) const {
        return result_type(a0);
    }

    template<class A0, class A1>
    result_type operator()(A0& a0, A1& a1) const {
        return result_type(a0, a1);
    }

    template<class A0, class A1, class A2>
    result_type operator()(A0& a0, A1& a1, A2& a2) const {
        return result_type(a0, a1, a2);
    }

    template<class A0, class A1, class A2, class A3>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3) const {
        return result_type(a0, a1, a2, a3);
    }

    template<class A0, class A1, class A2, class A3, class A4>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4) const {
        return result_type(a0, a1, a2, a3, a4);
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4,
        A5& a5) const {
        return result_type(a0, a1, a2, a3, a4, a5);
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6) const {
        return result_type(a0, a1, a2, a3, a4, a5, a6);
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7) const {
        return result_type(a0, a1, a2, a3, a4, a5, a6, a7);
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7, class A8>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7, A8& a8) const {
        return result_type(a0, a1, a2, a3, a4, a5, a6, a7, a8);
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7, class A8, class A9>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7, A8& a8, A9& a9) const {
        return result_type(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }
#endif
};

template<class T>
class value_factory<T&> { };

} /* boost */

#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

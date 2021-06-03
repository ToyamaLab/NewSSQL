<<<<<<< HEAD
/*=============================================================================
    Copyright (c) 2007 Tobias Schwinger
  
    Use modification and distribution are subject to the Boost Software 
    License, Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
    http://www.boost.org/LICENSE_1_0.txt).
==============================================================================*/

#ifndef BOOST_FUNCTIONAL_FACTORY_HPP_INCLUDED
#   ifndef BOOST_PP_IS_ITERATING

#     include <boost/preprocessor/iteration/iterate.hpp>
#     include <boost/preprocessor/repetition/enum_params.hpp>
#     include <boost/preprocessor/repetition/enum_binary_params.hpp>

#     include <new>
#     include <boost/pointee.hpp>
#     include <boost/get_pointer.hpp>
#     include <boost/non_type.hpp>
#     include <boost/type_traits/remove_cv.hpp>

#     if defined(BOOST_FUNCTIONAL_FACTORY_SUPPORT_NONE_T)
#       include <boost/none_t.hpp>
#     endif

#     ifndef BOOST_FUNCTIONAL_FACTORY_MAX_ARITY
#       define BOOST_FUNCTIONAL_FACTORY_MAX_ARITY 10
#     elif BOOST_FUNCTIONAL_FACTORY_MAX_ARITY < 3
#       undef  BOOST_FUNCTIONAL_FACTORY_MAX_ARITY
#       define BOOST_FUNCTIONAL_FACTORY_MAX_ARITY 3
#     endif

namespace boost
{
    enum factory_alloc_propagation
    {
        factory_alloc_for_pointee_and_deleter,
        factory_passes_alloc_to_smart_pointer
    };

#if defined(BOOST_FUNCTIONAL_FACTORY_SUPPORT_NONE_T)
    template< typename Pointer, class Allocator = boost::none_t,
        factory_alloc_propagation AP = factory_alloc_for_pointee_and_deleter >
    class factory;
#else
    template< typename Pointer, class Allocator = void,
        factory_alloc_propagation AP = factory_alloc_for_pointee_and_deleter >
    class factory;
#endif

    //----- ---- --- -- - -  -   -

    template< typename Pointer, factory_alloc_propagation AP >
    class factory<Pointer, void, AP>
    {
      public:
        typedef typename boost::remove_cv<Pointer>::type result_type;
        typedef typename boost::pointee<result_type>::type value_type;

        factory()
        { }

#     define BOOST_PP_FILENAME_1 <boost/functional/factory.hpp>
#     define BOOST_PP_ITERATION_LIMITS (0,BOOST_FUNCTIONAL_FACTORY_MAX_ARITY)
#     include BOOST_PP_ITERATE()
    };

#if defined(BOOST_FUNCTIONAL_FACTORY_SUPPORT_NONE_T)
    template< typename Pointer, factory_alloc_propagation AP >
    class factory<Pointer, boost::none_t, AP>
        : public factory<Pointer, void, AP>
    {};
#endif

    template< class Pointer, class Allocator, factory_alloc_propagation AP >
    class factory
        : private Allocator::template rebind< typename boost::pointee<
            typename boost::remove_cv<Pointer>::type >::type >::other
    {
      public:
        typedef typename boost::remove_cv<Pointer>::type result_type;
        typedef typename boost::pointee<result_type>::type value_type;

        typedef typename Allocator::template rebind<value_type>::other
            allocator_type;

        explicit factory(allocator_type const & a = allocator_type())
          : allocator_type(a)
        { }

      private:

        struct deleter
            : allocator_type
        {
            inline deleter(allocator_type const& that) 
              : allocator_type(that)
            { }

            allocator_type& get_allocator() const
            {
                return *const_cast<allocator_type*>(
                    static_cast<allocator_type const*>(this));
            }

            void operator()(value_type* ptr) const
            {
                if (!! ptr) ptr->~value_type();
                const_cast<allocator_type*>(static_cast<allocator_type const*>(
                    this))->deallocate(ptr,1);
            }
        };

        inline allocator_type& get_allocator() const
        {
            return *const_cast<allocator_type*>(
                static_cast<allocator_type const*>(this));
        }

        inline result_type make_pointer(value_type* ptr, boost::non_type<
            factory_alloc_propagation,factory_passes_alloc_to_smart_pointer>)
        const
        {
            return result_type(ptr,deleter(this->get_allocator()));
        }
        inline result_type make_pointer(value_type* ptr, boost::non_type<
            factory_alloc_propagation,factory_alloc_for_pointee_and_deleter>)
        const
        {
            return result_type(ptr,deleter(this->get_allocator()),
                this->get_allocator());
        }

      public:

#     define BOOST_TMP_MACRO
#     define BOOST_PP_FILENAME_1 <boost/functional/factory.hpp>
#     define BOOST_PP_ITERATION_LIMITS (0,BOOST_FUNCTIONAL_FACTORY_MAX_ARITY)
#     include BOOST_PP_ITERATE()
#     undef BOOST_TMP_MACRO
    };

    template< typename Pointer, class Allocator, factory_alloc_propagation AP > 
    class factory<Pointer&, Allocator, AP>;
    // forbidden, would create a dangling reference
}

#     define BOOST_FUNCTIONAL_FACTORY_HPP_INCLUDED
#   else // defined(BOOST_PP_IS_ITERATING)
#     define N BOOST_PP_ITERATION()
#     if !defined(BOOST_TMP_MACRO)
#       if N > 0
    template< BOOST_PP_ENUM_PARAMS(N, typename T) >
#       endif
    inline result_type operator()(BOOST_PP_ENUM_BINARY_PARAMS(N,T,& a)) const
    {
        return result_type( new value_type(BOOST_PP_ENUM_PARAMS(N,a)) );
    }
#     else // defined(BOOST_TMP_MACRO)
#       if N > 0
    template< BOOST_PP_ENUM_PARAMS(N, typename T) >
#       endif
    inline result_type operator()(BOOST_PP_ENUM_BINARY_PARAMS(N,T,& a)) const
    {
        value_type* memory = this->get_allocator().allocate(1);
        try
        { 
            return make_pointer(
                new(memory) value_type(BOOST_PP_ENUM_PARAMS(N,a)),
                boost::non_type<factory_alloc_propagation,AP>() );
        }
        catch (...) { this->get_allocator().deallocate(memory,1); throw; }
    }
#     endif
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
#ifndef BOOST_FUNCTIONAL_FACTORY_HPP
#define BOOST_FUNCTIONAL_FACTORY_HPP

#include <boost/config.hpp>
#include <boost/core/empty_value.hpp>
#include <boost/core/pointer_traits.hpp>
#include <boost/type_traits/remove_cv.hpp>
#if !defined(BOOST_NO_CXX11_ALLOCATOR)
#include <memory>
#endif
#include <new>
#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
#include <utility>
#endif

namespace boost {

enum factory_alloc_propagation {
    factory_alloc_for_pointee_and_deleter,
    factory_passes_alloc_to_smart_pointer
};

namespace detail {

template<factory_alloc_propagation>
struct fc_tag { };

#if !defined(BOOST_NO_CXX11_ALLOCATOR)
template<class A, class T>
struct fc_rebind {
    typedef typename std::allocator_traits<A>::template rebind_alloc<T> type;
};

template<class A>
struct fc_pointer {
    typedef typename std::allocator_traits<A>::pointer type;
};
#else
template<class A, class T>
struct fc_rebind {
    typedef typename A::template rebind<T>::other type;
};

template<class A>
struct fc_pointer {
    typedef typename A::pointer type;
};
#endif

#if !defined(BOOST_NO_CXX11_ALLOCATOR) && \
    !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
template<class A, class T>
inline void
fc_destroy(A& a, T* p)
{
    std::allocator_traits<A>::destroy(a, p);
}
#else
template<class A, class T>
inline void
fc_destroy(A&, T* p)
{
    p->~T();
}
#endif

#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
#if !defined(BOOST_NO_CXX11_ALLOCATOR)
template<class A, class T, class... Args>
inline void
fc_construct(A& a, T* p, Args&&... args)
{
    std::allocator_traits<A>::construct(a, p, std::forward<Args>(args)...);
}
#else
template<class A, class T, class... Args>
inline void
fc_construct(A&, T* p, Args&&... args)
{
    ::new((void*)p) T(std::forward<Args>(args)...);
}
#endif
#endif

template<class A>
class fc_delete
    : boost::empty_value<A> {
    typedef boost::empty_value<A> base;

public:
    explicit fc_delete(const A& a) BOOST_NOEXCEPT
        : base(boost::empty_init_t(), a) { }

    void operator()(typename fc_pointer<A>::type p) {
        boost::detail::fc_destroy(base::get(), boost::to_address(p));
        base::get().deallocate(p, 1);
    }
};

template<class R, class A>
class fc_allocate {
public:
    explicit fc_allocate(const A& a)
        : a_(a)
        , p_(a_.allocate(1)) { }

    ~fc_allocate() {
        if (p_) {
            a_.deallocate(p_, 1);
        }
    }

    A& state() BOOST_NOEXCEPT {
        return a_;
    }

    typename A::value_type* get() const BOOST_NOEXCEPT {
        return boost::to_address(p_);
    }

    R release(fc_tag<factory_alloc_for_pointee_and_deleter>) {
        return R(release(), fc_delete<A>(a_), a_);
    }

    R release(fc_tag<factory_passes_alloc_to_smart_pointer>) {
        return R(release(), fc_delete<A>(a_));
    }

private:
    typedef typename fc_pointer<A>::type pointer;

    pointer release() BOOST_NOEXCEPT {
        pointer p = p_;
        p_ = pointer();
        return p;
    }

    fc_allocate(const fc_allocate&);
    fc_allocate& operator=(const fc_allocate&);

    A a_;
    pointer p_;
};

} /* detail */

template<class Pointer, class Allocator = void,
    factory_alloc_propagation Policy = factory_alloc_for_pointee_and_deleter>
class factory;

template<class Pointer, factory_alloc_propagation Policy>
class factory<Pointer, void, Policy> {
public:
    typedef typename remove_cv<Pointer>::type result_type;

private:
    typedef typename pointer_traits<result_type>::element_type type;

public:
#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
    template<class... Args>
    result_type operator()(Args&&... args) const {
        return result_type(new type(std::forward<Args>(args)...));
    }
#else
    result_type operator()() const {
        return result_type(new type());
    }

    template<class A0>
    result_type operator()(A0& a0) const {
        return result_type(new type(a0));
    }

    template<class A0, class A1>
    result_type operator()(A0& a0, A1& a1) const {
        return result_type(new type(a0, a1));
    }

    template<class A0, class A1, class A2>
    result_type operator()(A0& a0, A1& a1, A2& a2) const {
        return result_type(new type(a0, a1, a2));
    }

    template<class A0, class A1, class A2, class A3>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3) const {
        return result_type(new type(a0, a1, a2, a3));
    }

    template<class A0, class A1, class A2, class A3, class A4>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4) const {
        return result_type(new type(a0, a1, a2, a3, a4));
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4,
        A5& a5) const {
        return result_type(new type(a0, a1, a2, a3, a4, a5));
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6) const {
        return result_type(new type(a0, a1, a2, a3, a4, a5, a6));
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7) const {
        return result_type(new type(a0, a1, a2, a3, a4, a5, a6, a7));
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7, class A8>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7, A8& a8) const {
        return result_type(new type(a0, a1, a2, a3, a4, a5, a6, a7, a8));
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7, class A8, class A9>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7, A8& a8, A9& a9) const {
        return result_type(new type(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9));
    }
#endif
};

template<class Pointer, class Allocator, factory_alloc_propagation Policy>
class factory
    : empty_value<typename detail::fc_rebind<Allocator,
        typename pointer_traits<typename
            remove_cv<Pointer>::type>::element_type>::type> {
public:
    typedef typename remove_cv<Pointer>::type result_type;

private:
    typedef typename pointer_traits<result_type>::element_type type;
    typedef typename detail::fc_rebind<Allocator, type>::type allocator;
    typedef empty_value<allocator> base;

public:
    factory() BOOST_NOEXCEPT
        : base(empty_init_t()) { }

    explicit factory(const Allocator& a) BOOST_NOEXCEPT
        : base(empty_init_t(), a) { }

#if !defined(BOOST_NO_CXX11_RVALUE_REFERENCES) && \
    !defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES)
    template<class... Args>
    result_type operator()(Args&&... args) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        detail::fc_construct(s.state(), s.get(), std::forward<Args>(args)...);
        return s.release(detail::fc_tag<Policy>());
    }
#else
    result_type operator()() const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type();
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0>
    result_type operator()(A0& a0) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1>
    result_type operator()(A0& a0, A1& a1) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2>
    result_type operator()(A0& a0, A1& a1, A2& a2) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3, class A4>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3, a4);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4,
        A5& a5) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3, a4, a5);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3, a4, a5, a6);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3, a4, a5, a6, a7);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7, class A8>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7, A8& a8) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        return s.release(detail::fc_tag<Policy>());
    }

    template<class A0, class A1, class A2, class A3, class A4, class A5,
        class A6, class A7, class A8, class A9>
    result_type operator()(A0& a0, A1& a1, A2& a2, A3& a3, A4& a4, A5& a5,
        A6& a6, A7& a7, A8& a8, A9& a9) const {
        detail::fc_allocate<result_type, allocator> s(base::get());
        ::new((void*)s.get()) type(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9);
        return s.release(detail::fc_tag<Policy>());
    }
#endif
};

template<class Pointer, class Allocator, factory_alloc_propagation Policy>
class factory<Pointer&, Allocator, Policy> { };

} /* boost */

#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

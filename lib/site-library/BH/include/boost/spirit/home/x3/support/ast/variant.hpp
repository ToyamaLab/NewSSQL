/*=============================================================================
    Copyright (c) 2001-2014 Joel de Guzman

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
==============================================================================*/
#if !defined(BOOST_SPIRIT_X3_VARIANT_AUGUST_6_2011_0859AM)
#define BOOST_SPIRIT_X3_VARIANT_AUGUST_6_2011_0859AM

<<<<<<< HEAD
#include <boost/variant.hpp>
#include <boost/mpl/list.hpp>
#include <boost/type_traits/is_base_of.hpp>
=======
#include <boost/config.hpp>
#include <boost/variant.hpp>
#include <boost/mpl/list.hpp>
#include <utility>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <type_traits>

///////////////////////////////////////////////////////////////////////////////
namespace boost { namespace spirit { namespace x3
{
    template <typename T>
    class forward_ast
    {
    public:

        typedef T type;

    public:

        forward_ast() : p_(new T) {}

        forward_ast(forward_ast const& operand)
            : p_(new T(operand.get())) {}

<<<<<<< HEAD
        forward_ast(forward_ast&& operand)
=======
        forward_ast(forward_ast&& operand) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            : p_(operand.p_)
        {
            operand.p_ = 0;
        }

        forward_ast(T const& operand)
            : p_(new T(operand)) {}

        forward_ast(T&& operand)
            : p_(new T(std::move(operand))) {}

        ~forward_ast()
        {
            boost::checked_delete(p_);
        }

<<<<<<< HEAD
        forward_ast& operator=(forward_ast const& rhs)
=======
        forward_ast& operator=(forward_ast const& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_copy_assignable<T>::value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            assign(rhs.get());
            return *this;
        }

        void swap(forward_ast& operand) BOOST_NOEXCEPT
        {
            T* temp = operand.p_;
            operand.p_ = p_;
            p_ = temp;
        }

<<<<<<< HEAD
        forward_ast& operator=(T const& rhs)
=======
        forward_ast& operator=(T const& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_copy_assignable<T>::value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            assign(rhs);
            return *this;
        }

        forward_ast& operator=(forward_ast&& rhs) BOOST_NOEXCEPT
        {
            swap(rhs);
            return *this;
        }

<<<<<<< HEAD
        forward_ast& operator=(T&& rhs)
=======
        forward_ast& operator=(T&& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_move_assignable<T>::value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            get() = std::move(rhs);
            return *this;
        }

<<<<<<< HEAD
        T& get() { return *get_pointer(); }
        const T& get() const { return *get_pointer(); }

        T* get_pointer() { return p_; }
        const T* get_pointer() const { return p_; }

        operator T const&() const { return this->get(); }
        operator T&() { return this->get(); }

    private:

        void assign(const T& rhs)
=======
        T& get() BOOST_NOEXCEPT { return *get_pointer(); }
        const T& get() const BOOST_NOEXCEPT { return *get_pointer(); }

        T* get_pointer() BOOST_NOEXCEPT { return p_; }
        const T* get_pointer() const BOOST_NOEXCEPT { return p_; }

        operator T const&() const BOOST_NOEXCEPT { return this->get(); }
        operator T&() BOOST_NOEXCEPT { return this->get(); }

    private:

        void assign(const T& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_copy_assignable<T>::value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            this->get() = rhs;
        }

        T* p_;
    };

    // function template swap
    //
    // Swaps two forward_ast<T> objects of the same type T.
    //
    template <typename T>
    inline void swap(forward_ast<T>& lhs, forward_ast<T>& rhs) BOOST_NOEXCEPT
    {
        lhs.swap(rhs);
    }

    namespace detail
    {
        template <typename T>
        struct remove_forward : mpl::identity<T>
        {};

        template <typename T>
        struct remove_forward<forward_ast<T>> : mpl::identity<T>
        {};
    }

<<<<<<< HEAD
=======
#if defined(BOOST_MSVC)
# pragma warning(push)
# pragma warning(disable: 4521) // multiple copy constructors specified
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template <typename ...Types>
    struct variant
    {
        // tell spirit that this is an adapted variant
        struct adapted_variant_tag;

        using variant_type = boost::variant<Types...>;
        using types        = mpl::list<typename detail::remove_forward<Types>::type...>;
        using base_type    = variant; // The current instantiation

        template<typename T>
        using non_self_t // used only for SFINAE checks below
            = std::enable_if_t<!(std::is_base_of<base_type
                                                ,std::remove_reference_t<T>
                                                >
                                                ::value)
                              >;

<<<<<<< HEAD
        variant() : var() {}

        template <typename T, class = non_self_t<T>>
        explicit variant(T const& rhs)
            : var(rhs) {}

        template <typename T, class = non_self_t<T>>
        explicit variant(T&& rhs)
            : var(std::forward<T>(rhs)) {}

        variant(variant const& rhs)
            : var(rhs.var) {}

        variant(variant& rhs)
            : var(rhs.var) {}

        variant(variant&& rhs)
            : var(std::forward<variant_type>(rhs.var)) {}

        variant& operator=(variant const& rhs)
=======
        variant() BOOST_NOEXCEPT_IF(std::is_nothrow_default_constructible<variant_type>::value) : var() {}

        template <typename T, class = non_self_t<T>>
        explicit variant(T const& rhs) BOOST_NOEXCEPT_IF((std::is_nothrow_constructible<variant_type, T const&>::value))
            : var(rhs) {}

        template <typename T, class = non_self_t<T>>
        explicit variant(T&& rhs) BOOST_NOEXCEPT_IF((std::is_nothrow_constructible<variant_type, T&&>::value))
            : var(std::forward<T>(rhs)) {}

        variant(variant const& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_copy_constructible<variant_type>::value)
            : var(rhs.var) {}

        variant(variant& rhs) BOOST_NOEXCEPT_IF((std::is_nothrow_constructible<variant_type, variant_type&>::value))
            : var(rhs.var) {}

        variant(variant&& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_move_constructible<variant_type>::value)
            : var(std::move(rhs.var)) {}

        variant& operator=(variant const& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_copy_assignable<variant_type>::value)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            var = rhs.get();
            return *this;
        }

<<<<<<< HEAD
        variant& operator=(variant&& rhs)
        {
            var = std::forward<variant_type>(rhs.get());
=======
        variant& operator=(variant&& rhs) BOOST_NOEXCEPT_IF(std::is_nothrow_move_assignable<variant_type>::value)
        {
            var = std::move(rhs.get());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            return *this;
        }

        template <typename T, class = non_self_t<T>>
<<<<<<< HEAD
        variant& operator=(T const& rhs)
=======
        variant& operator=(T const& rhs) BOOST_NOEXCEPT_IF((std::is_nothrow_assignable<variant_type, T const&>::value))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            var = rhs;
            return *this;
        }

        template <typename T, class = non_self_t<T>>
<<<<<<< HEAD
        variant& operator=(T&& rhs)
=======
        variant& operator=(T&& rhs) BOOST_NOEXCEPT_IF((std::is_nothrow_assignable<variant_type, T&&>::value))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            var = std::forward<T>(rhs);
            return *this;
        }

        template <typename F>
        typename F::result_type apply_visitor(F const& v)
        {
            return var.apply_visitor(v);
        }

        template <typename F>
        typename F::result_type apply_visitor(F const& v) const
        {
            return var.apply_visitor(v);
        }

        template <typename F>
        typename F::result_type apply_visitor(F& v)
        {
            return var.apply_visitor(v);
        }

        template <typename F>
        typename F::result_type apply_visitor(F& v) const
        {
            return var.apply_visitor(v);
        }

<<<<<<< HEAD
        variant_type const& get() const
=======
        variant_type const& get() const BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return var;
        }

<<<<<<< HEAD
        variant_type& get()
=======
        variant_type& get() BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return var;
        }

        void swap(variant& rhs) BOOST_NOEXCEPT
        {
            var.swap(rhs.var);
        }

        variant_type var;
    };
<<<<<<< HEAD
=======
#if defined(BOOST_MSVC)
# pragma warning(pop)
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}}}

namespace boost
{
    template <typename T, typename ...Types>
    inline T const&
<<<<<<< HEAD
    get(boost::spirit::x3::variant<Types...> const& x)
=======
    get(boost::spirit::x3::variant<Types...> const& x) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return boost::get<T>(x.get());
    }

    template <typename T, typename ...Types>
    inline T&
<<<<<<< HEAD
    get(boost::spirit::x3::variant<Types...>& x)
=======
    get(boost::spirit::x3::variant<Types...>& x) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return boost::get<T>(x.get());
    }

    template <typename T, typename ...Types>
    inline T const*
<<<<<<< HEAD
    get(boost::spirit::x3::variant<Types...> const* x)
=======
    get(boost::spirit::x3::variant<Types...> const* x) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return boost::get<T>(&x->get());
    }

    template <typename T, typename ...Types>
    inline T*
<<<<<<< HEAD
    get(boost::spirit::x3::variant<Types...>* x)
=======
    get(boost::spirit::x3::variant<Types...>* x) BOOST_NOEXCEPT
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return boost::get<T>(&x->get());
    }
}

#endif

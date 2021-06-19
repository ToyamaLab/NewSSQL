/*=============================================================================
    Copyright (c) 2007-2011 Hartmut Kaiser
    Copyright (c) Christopher Diggins 2005
    Copyright (c) Pablo Aguilar 2005
    Copyright (c) Kevlin Henney 2001

    Distributed under the Boost Software License, Version 1.0. (See accompanying
    file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)

    The class boost::spirit::hold_any is built based on the any class
    published here: http://www.codeproject.com/cpp/dynamic_typing.asp. It adds
    support for std streaming operator<<() and operator>>().
==============================================================================*/
#if !defined(BOOST_SPIRIT_HOLD_ANY_MAY_02_2007_0857AM)
#define BOOST_SPIRIT_HOLD_ANY_MAY_02_2007_0857AM

#if defined(_MSC_VER)
#pragma once
#endif

#include <boost/config.hpp>
#include <boost/type_traits/remove_reference.hpp>
#include <boost/type_traits/is_reference.hpp>
#include <boost/throw_exception.hpp>
#include <boost/static_assert.hpp>
#include <boost/mpl/bool.hpp>
#include <boost/assert.hpp>
<<<<<<< HEAD
#include <boost/detail/sp_typeinfo.hpp>
=======
#include <boost/core/typeinfo.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#include <stdexcept>
#include <typeinfo>
#include <algorithm>
#include <iosfwd>

///////////////////////////////////////////////////////////////////////////////
#if BOOST_WORKAROUND(BOOST_MSVC, >= 1400)
# pragma warning(push)
# pragma warning(disable: 4100)   // 'x': unreferenced formal parameter
# pragma warning(disable: 4127)   // conditional expression is constant
#endif

///////////////////////////////////////////////////////////////////////////////
namespace boost { namespace spirit
{
    struct bad_any_cast
      : std::bad_cast
    {
<<<<<<< HEAD
        bad_any_cast(boost::detail::sp_typeinfo const& src, boost::detail::sp_typeinfo const& dest)
          : from(src.name()), to(dest.name())
        {}

        virtual const char* what() const throw() { return "bad any cast"; }
=======
        bad_any_cast(boost::core::typeinfo const& src, boost::core::typeinfo const& dest)
          : from(src.name()), to(dest.name())
        {}

        virtual const char* what() const BOOST_NOEXCEPT_OR_NOTHROW { return "bad any cast"; }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        const char* from;
        const char* to;
    };

    namespace detail
    {
        // function pointer table
        template <typename Char>
        struct fxn_ptr_table
        {
<<<<<<< HEAD
            boost::detail::sp_typeinfo const& (*get_type)();
=======
            boost::core::typeinfo const& (*get_type)();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            void (*static_delete)(void**);
            void (*destruct)(void**);
            void (*clone)(void* const*, void**);
            void (*move)(void* const*, void**);
            std::basic_istream<Char>& (*stream_in)(std::basic_istream<Char>&, void**);
            std::basic_ostream<Char>& (*stream_out)(std::basic_ostream<Char>&, void* const*);
        };

        // static functions for small value-types
        template <typename Small>
        struct fxns;

        template <>
        struct fxns<mpl::true_>
        {
            template<typename T, typename Char>
            struct type
            {
<<<<<<< HEAD
                static boost::detail::sp_typeinfo const& get_type()
                {
                    return BOOST_SP_TYPEID(T);
=======
                static boost::core::typeinfo const& get_type()
                {
                    return BOOST_CORE_TYPEID(T);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                }
                static void static_delete(void** x)
                {
                    reinterpret_cast<T*>(x)->~T();
                }
                static void destruct(void** x)
                {
                    reinterpret_cast<T*>(x)->~T();
                }
                static void clone(void* const* src, void** dest)
                {
                    new (dest) T(*reinterpret_cast<T const*>(src));
                }
                static void move(void* const* src, void** dest)
                {
                    *reinterpret_cast<T*>(dest) =
                        *reinterpret_cast<T const*>(src);
                }
<<<<<<< HEAD
                static std::basic_istream<Char>& 
=======
                static std::basic_istream<Char>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                stream_in (std::basic_istream<Char>& i, void** obj)
                {
                    i >> *reinterpret_cast<T*>(obj);
                    return i;
                }
<<<<<<< HEAD
                static std::basic_ostream<Char>& 
=======
                static std::basic_ostream<Char>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                stream_out(std::basic_ostream<Char>& o, void* const* obj)
                {
                    o << *reinterpret_cast<T const*>(obj);
                    return o;
                }
            };
        };

        // static functions for big value-types (bigger than a void*)
        template <>
        struct fxns<mpl::false_>
        {
            template<typename T, typename Char>
            struct type
            {
<<<<<<< HEAD
                static boost::detail::sp_typeinfo const& get_type()
                {
                    return BOOST_SP_TYPEID(T);
=======
                static boost::core::typeinfo const& get_type()
                {
                    return BOOST_CORE_TYPEID(T);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                }
                static void static_delete(void** x)
                {
                    // destruct and free memory
                    delete (*reinterpret_cast<T**>(x));
                }
                static void destruct(void** x)
                {
                    // destruct only, we'll reuse memory
                    (*reinterpret_cast<T**>(x))->~T();
                }
                static void clone(void* const* src, void** dest)
                {
                    *dest = new T(**reinterpret_cast<T* const*>(src));
                }
                static void move(void* const* src, void** dest)
                {
                    **reinterpret_cast<T**>(dest) =
                        **reinterpret_cast<T* const*>(src);
                }
<<<<<<< HEAD
                static std::basic_istream<Char>& 
=======
                static std::basic_istream<Char>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                stream_in(std::basic_istream<Char>& i, void** obj)
                {
                    i >> **reinterpret_cast<T**>(obj);
                    return i;
                }
<<<<<<< HEAD
                static std::basic_ostream<Char>& 
=======
                static std::basic_ostream<Char>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                stream_out(std::basic_ostream<Char>& o, void* const* obj)
                {
                    o << **reinterpret_cast<T* const*>(obj);
                    return o;
                }
            };
        };

        template <typename T>
        struct get_table
        {
            typedef mpl::bool_<(sizeof(T) <= sizeof(void*))> is_small;

            template <typename Char>
            static fxn_ptr_table<Char>* get()
            {
                static fxn_ptr_table<Char> static_table =
                {
                    fxns<is_small>::template type<T, Char>::get_type,
                    fxns<is_small>::template type<T, Char>::static_delete,
                    fxns<is_small>::template type<T, Char>::destruct,
                    fxns<is_small>::template type<T, Char>::clone,
                    fxns<is_small>::template type<T, Char>::move,
                    fxns<is_small>::template type<T, Char>::stream_in,
                    fxns<is_small>::template type<T, Char>::stream_out
                };
                return &static_table;
            }
        };

        ///////////////////////////////////////////////////////////////////////
        struct empty {};

        template <typename Char>
        inline std::basic_istream<Char>&
        operator>> (std::basic_istream<Char>& i, empty&)
        {
            // If this assertion fires you tried to insert from a std istream
            // into an empty hold_any instance. This simply can't work, because
            // there is no way to figure out what type to extract from the
            // stream.
            // The only way to make this work is to assign an arbitrary
            // value of the required type to the hold_any instance you want to
            // stream to. This assignment has to be executed before the actual
            // call to the operator>>().
<<<<<<< HEAD
            BOOST_ASSERT(false && 
=======
            BOOST_ASSERT(false &&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                "Tried to insert from a std istream into an empty "
                "hold_any instance");
            return i;
        }

        template <typename Char>
        inline std::basic_ostream<Char>&
        operator<< (std::basic_ostream<Char>& o, empty const&)
        {
            return o;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    template <typename Char>
    class basic_hold_any
    {
    public:
        // constructors
        template <typename T>
        explicit basic_hold_any(T const& x)
          : table(spirit::detail::get_table<T>::template get<Char>()), object(0)
        {
<<<<<<< HEAD
            if (spirit::detail::get_table<T>::is_small::value)
                new (&object) T(x);
            else
                object = new T(x);
=======
            new_object(object, x,
                typename spirit::detail::get_table<T>::is_small());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        basic_hold_any()
          : table(spirit::detail::get_table<spirit::detail::empty>::template get<Char>()),
            object(0)
        {
        }

        basic_hold_any(basic_hold_any const& x)
          : table(spirit::detail::get_table<spirit::detail::empty>::template get<Char>()),
            object(0)
        {
            assign(x);
        }

        ~basic_hold_any()
        {
            table->static_delete(&object);
        }

        // assignment
        basic_hold_any& assign(basic_hold_any const& x)
        {
            if (&x != this) {
                // are we copying between the same type?
                if (table == x.table) {
                    // if so, we can avoid reallocation
                    table->move(&x.object, &object);
                }
                else {
                    reset();
                    x.table->clone(&x.object, &object);
                    table = x.table;
                }
            }
            return *this;
        }

        template <typename T>
        basic_hold_any& assign(T const& x)
        {
            // are we copying between the same type?
            spirit::detail::fxn_ptr_table<Char>* x_table =
                spirit::detail::get_table<T>::template get<Char>();
            if (table == x_table) {
            // if so, we can avoid deallocating and re-use memory
                table->destruct(&object);    // first destruct the old content
                if (spirit::detail::get_table<T>::is_small::value) {
                    // create copy on-top of object pointer itself
                    new (&object) T(x);
                }
                else {
                    // create copy on-top of old version
                    new (object) T(x);
                }
            }
            else {
                if (spirit::detail::get_table<T>::is_small::value) {
                    // create copy on-top of object pointer itself
                    table->destruct(&object); // first destruct the old content
                    new (&object) T(x);
                }
                else {
                    reset();                  // first delete the old content
                    object = new T(x);
                }
                table = x_table;      // update table pointer
            }
            return *this;
        }

<<<<<<< HEAD
=======
        template <typename T>
        static void new_object(void*& object, T const& x, mpl::true_)
        {
            new (&object) T(x);
        }

        template <typename T>
        static void new_object(void*& object, T const& x, mpl::false_)
        {
            object = new T(x);
        }

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        // assignment operator
#ifdef BOOST_HAS_RVALUE_REFS
        template <typename T>
        basic_hold_any& operator=(T&& x)
        {
            return assign(std::forward<T>(x));
        }
#else
        template <typename T>
        basic_hold_any& operator=(T& x)
        {
            return assign(x);
        }

        template <typename T>
        basic_hold_any& operator=(T const& x)
        {
            return assign(x);
        }
#endif
<<<<<<< HEAD
=======
        // copy assignment operator
        basic_hold_any& operator=(basic_hold_any const& x)
        {
            return assign(x);
        }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        // utility functions
        basic_hold_any& swap(basic_hold_any& x)
        {
            std::swap(table, x.table);
            std::swap(object, x.object);
            return *this;
        }

<<<<<<< HEAD
        boost::detail::sp_typeinfo const& type() const
=======
        boost::core::typeinfo const& type() const
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return table->get_type();
        }

        template <typename T>
        T const& cast() const
        {
<<<<<<< HEAD
            if (type() != BOOST_SP_TYPEID(T))
              throw bad_any_cast(type(), BOOST_SP_TYPEID(T));
=======
            if (type() != BOOST_CORE_TYPEID(T))
              throw bad_any_cast(type(), BOOST_CORE_TYPEID(T));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

            return spirit::detail::get_table<T>::is_small::value ?
                *reinterpret_cast<T const*>(&object) :
                *reinterpret_cast<T const*>(object);
        }

// implicit casting is disabled by default for compatibility with boost::any
#ifdef BOOST_SPIRIT_ANY_IMPLICIT_CASTING
        // automatic casting operator
        template <typename T>
        operator T const& () const { return cast<T>(); }
#endif // implicit casting

        bool empty() const
        {
            return table == spirit::detail::get_table<spirit::detail::empty>::template get<Char>();
        }

        void reset()
        {
            if (!empty())
            {
                table->static_delete(&object);
                table = spirit::detail::get_table<spirit::detail::empty>::template get<Char>();
                object = 0;
            }
        }

    // these functions have been added in the assumption that the embedded
    // type has a corresponding operator defined, which is completely safe
    // because spirit::hold_any is used only in contexts where these operators
    // do exist
        template <typename Char_>
<<<<<<< HEAD
        friend inline std::basic_istream<Char_>& 
=======
        friend inline std::basic_istream<Char_>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        operator>> (std::basic_istream<Char_>& i, basic_hold_any<Char_>& obj)
        {
            return obj.table->stream_in(i, &obj.object);
        }

        template <typename Char_>
<<<<<<< HEAD
        friend inline std::basic_ostream<Char_>& 
=======
        friend inline std::basic_ostream<Char_>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        operator<< (std::basic_ostream<Char_>& o, basic_hold_any<Char_> const& obj)
        {
            return obj.table->stream_out(o, &obj.object);
        }

#ifndef BOOST_NO_MEMBER_TEMPLATE_FRIENDS
    private: // types
        template <typename T, typename Char_>
        friend T* any_cast(basic_hold_any<Char_> *);
#else
    public: // types (public so any_cast can be non-friend)
#endif
        // fields
        spirit::detail::fxn_ptr_table<Char>* table;
        void* object;
    };

    // boost::any-like casting
    template <typename T, typename Char>
    inline T* any_cast (basic_hold_any<Char>* operand)
    {
<<<<<<< HEAD
        if (operand && operand->type() == BOOST_SP_TYPEID(T)) {
=======
        if (operand && operand->type() == BOOST_CORE_TYPEID(T)) {
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            return spirit::detail::get_table<T>::is_small::value ?
                reinterpret_cast<T*>(&operand->object) :
                reinterpret_cast<T*>(operand->object);
        }
        return 0;
    }

    template <typename T, typename Char>
    inline T const* any_cast(basic_hold_any<Char> const* operand)
    {
        return any_cast<T>(const_cast<basic_hold_any<Char>*>(operand));
    }

    template <typename T, typename Char>
    T any_cast(basic_hold_any<Char>& operand)
    {
        typedef BOOST_DEDUCED_TYPENAME remove_reference<T>::type nonref;


        nonref* result = any_cast<nonref>(&operand);
        if(!result)
<<<<<<< HEAD
            boost::throw_exception(bad_any_cast(operand.type(), BOOST_SP_TYPEID(T)));
=======
            boost::throw_exception(bad_any_cast(operand.type(), BOOST_CORE_TYPEID(T)));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        return *result;
    }

    template <typename T, typename Char>
    T const& any_cast(basic_hold_any<Char> const& operand)
    {
        typedef BOOST_DEDUCED_TYPENAME remove_reference<T>::type nonref;


        return any_cast<nonref const&>(const_cast<basic_hold_any<Char> &>(operand));
    }

    ///////////////////////////////////////////////////////////////////////////////
    // backwards compatibility
    typedef basic_hold_any<char> hold_any;
    typedef basic_hold_any<wchar_t> whold_any;

    namespace traits
    {
        template <typename T>
        struct is_hold_any : mpl::false_ {};

        template <typename Char>
        struct is_hold_any<basic_hold_any<Char> > : mpl::true_ {};
    }

}}    // namespace boost::spirit

///////////////////////////////////////////////////////////////////////////////
#if BOOST_WORKAROUND(BOOST_MSVC, >= 1400)
# pragma warning(pop)
#endif

#endif

// Boost.Assign library
//
//  Copyright Thorsten Ottosen 2003-2004. Use, modification and
//  distribution is subject to the Boost Software License, Version
//  1.0. (See accompanying file LICENSE_1_0.txt or copy at
//  http://www.boost.org/LICENSE_1_0.txt)
//
// For more information, see http://www.boost.org/libs/assign/
//


#ifndef BOOST_ASSIGN_LIST_OF_HPP
#define BOOST_ASSIGN_LIST_OF_HPP

#if defined(_MSC_VER)
# pragma once
#endif

#include <boost/assign/assignment_exception.hpp>
#include <boost/range/iterator_range.hpp>
#include <boost/config.hpp>
#include <boost/tuple/tuple.hpp>
#include <boost/type_traits/remove_const.hpp>
#include <boost/type_traits/remove_reference.hpp>
#include <boost/type_traits/is_reference.hpp>
#include <boost/static_assert.hpp>
<<<<<<< HEAD
#include <boost/type_traits/detail/yes_no_type.hpp>
#include <boost/type_traits/decay.hpp>
#include <boost/type_traits/is_array.hpp>
#include <boost/mpl/if.hpp>
#include <deque>
#include <cstddef>
#include <utility>
=======
#include <boost/throw_exception.hpp>
#include <boost/type_traits/detail/yes_no_type.hpp>
#include <boost/type_traits/decay.hpp>
#include <boost/type_traits/is_array.hpp>
#include <boost/utility/enable_if.hpp>
#include <boost/utility/declval.hpp>
#include <boost/mpl/if.hpp>
#include <boost/move/utility.hpp>
#include <deque>
#include <cstddef>
#include <utility>
#ifndef BOOST_NO_CXX11_HDR_ARRAY
#include <array>
#endif
#ifndef BOOST_NO_CXX11_HDR_INITIALIZER_LIST
#include <initializer_list>
#endif

// some gcc < 4.7 do not support all of the variadic features required for boost::assign
#if !(defined(BOOST_NO_CXX11_VARIADIC_TEMPLATES) || BOOST_WORKAROUND(BOOST_GCC, < 40700) \
       || defined(BOOST_NO_CXX11_RVALUE_REFERENCES))
# define BOOST_ASSIGN_USE_VARIADIC_TEMPLATES
#endif

#if !defined(BOOST_ASSIGN_USE_VARIADIC_TEMPLATES)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#include <boost/preprocessor/repetition/enum_binary_params.hpp>
#include <boost/preprocessor/repetition/enum_params.hpp>
#include <boost/preprocessor/iteration/local.hpp>

<<<<<<< HEAD
=======
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
// BCB requires full type definition for is_array<> to work correctly.
#include <boost/array.hpp>
#endif

namespace boost
{

// this here is necessary to avoid compiler error in <boost/array.hpp>
#if !BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    template< class T, std::size_t sz >
    class array;
<<<<<<< HEAD
#endif    
    
=======
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
namespace assign_detail
{
    /////////////////////////////////////////////////////////////////////////
    // Part 0: common conversion code
    /////////////////////////////////////////////////////////////////////////

    template< class T >
    struct assign_decay
    {
        //
        // Add constness to array parameters
        // to support string literals properly
        //
        typedef BOOST_DEDUCED_TYPENAME mpl::eval_if<
            ::boost::is_array<T>,
            ::boost::decay<const T>,
            ::boost::decay<T> >::type type;
    };
<<<<<<< HEAD
    
    template< class T, std::size_t sz >
    type_traits::yes_type assign_is_array( const array<T,sz>* );
    type_traits::no_type assign_is_array( ... );
    template< class T, class U >
    type_traits::yes_type assign_is_pair( const std::pair<T,U>* );
    type_traits::no_type assign_is_pair( ... ); 


    
=======

    template< class T, std::size_t sz >
    type_traits::yes_type assign_is_array( const array<T,sz>* );
#ifndef BOOST_NO_CXX11_HDR_ARRAY
    template< class T, std::size_t sz >
    type_traits::yes_type assign_is_array( const std::array<T, sz>* );
#endif
    type_traits::no_type assign_is_array( ... );
    template< class T, class U >
    type_traits::yes_type assign_is_pair( const std::pair<T,U>* );
    type_traits::no_type assign_is_pair( ... );



>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    struct array_type_tag
    {
    #if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    private:
      char dummy_;  // BCB would by default use 8 bytes
    #endif
    };
    struct adapter_type_tag
    {
    #if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    private:
      char dummy_;  // BCB would by default use 8 bytes
    #endif
    };
    struct pair_type_tag
    {
    #if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    private:
      char dummy_;  // BCB would by default use 8 bytes
    #endif
    };
    struct default_type_tag
    {
    #if BOOST_WORKAROUND(__BORLANDC__, BOOST_TESTED_AT(0x564))
    private:
      char dummy_;  // BCB would by default use 8 bytes
    #endif
    };

<<<<<<< HEAD

    
=======
#ifndef BOOST_NO_CXX11_HDR_INITIALIZER_LIST
    template< class C >
    struct is_initializer_list : boost::false_type {};

    template< class E >
    struct is_initializer_list< std::initializer_list<E> > : boost::true_type {};
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template< class DerivedTAssign, class Iterator >
    class converter
    {
    public: // Range operations
        typedef Iterator iterator;
        typedef Iterator const_iterator;
<<<<<<< HEAD
        
        iterator begin() const 
=======

        iterator begin() const
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return static_cast<const DerivedTAssign*>(this)->begin();
        }

        iterator end() const
        {
            return static_cast<const DerivedTAssign*>(this)->end();
        }
<<<<<<< HEAD
        
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    public:

        template< class Container >
        Container convert_to_container() const
        {
            static Container* c = 0;
<<<<<<< HEAD
            BOOST_STATIC_CONSTANT( bool, is_array_flag = sizeof( assign_detail::assign_is_array( c ) ) 
=======
            BOOST_STATIC_CONSTANT( bool, is_array_flag = sizeof( assign_detail::assign_is_array( c ) )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                                   == sizeof( type_traits::yes_type ) );

            typedef BOOST_DEDUCED_TYPENAME mpl::if_c< is_array_flag,
                                                      array_type_tag,
                                             default_type_tag >::type tag_type;

            return convert<Container>( c, tag_type() );
        }
<<<<<<< HEAD
        
    private:
        
=======

    private:

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template< class Container >
        Container convert( const Container*, default_type_tag ) const
        {

#if BOOST_WORKAROUND(BOOST_DINKUMWARE_STDLIB, == 1)
// old Dinkumware doesn't support iterator type as template
            Container result;
<<<<<<< HEAD
            iterator it  = begin(), 
=======
            iterator it  = begin(),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                     e   = end();
            while( it != e )
            {
                result.insert( result.end(), *it );
                ++it;
            }
            return result;
#else
            return Container( begin(), end() );
#endif
        }

        template< class Array >
        Array convert( const Array*, array_type_tag ) const
        {
            typedef BOOST_DEDUCED_TYPENAME Array::value_type value_type;

<<<<<<< HEAD
#if BOOST_WORKAROUND(BOOST_INTEL, <= 910 ) || BOOST_WORKAROUND(__SUNPRO_CC, <= 0x580 )
            BOOST_DEDUCED_TYPENAME remove_const<Array>::type ar;
#else
            Array ar;
#endif            
            const std::size_t sz = ar.size();
            if( sz < static_cast<const DerivedTAssign*>(this)->size() )
                throw assign::assignment_exception( "array initialized with too many elements" );
            std::size_t n = 0; 
            iterator i   = begin(), 
=======
#if BOOST_WORKAROUND(BOOST_INTEL, <= 910 ) || BOOST_WORKAROUND(__SUNPRO_CC, <= 0x5100 )
            BOOST_DEDUCED_TYPENAME remove_const<Array>::type ar;
#else
            Array ar;
#endif
            const std::size_t sz = ar.size();
            if( sz < static_cast<const DerivedTAssign*>(this)->size() )
                BOOST_THROW_EXCEPTION( assign::assignment_exception( "array initialized with too many elements" ) );
            std::size_t n = 0;
            iterator i   = begin(),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                     e   = end();
            for( ; i != e; ++i, ++n )
                ar[n] = *i;
            for( ; n < sz; ++n )
                ar[n] = value_type();
<<<<<<< HEAD
            return ar; 
=======
            return ar;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template< class Adapter >
        Adapter convert_to_adapter( const Adapter* = 0 ) const
        {
            Adapter a;
<<<<<<< HEAD
            iterator i   = begin(), 
=======
            iterator i   = begin(),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                     e   = end();
            for( ; i != e; ++i )
                a.push( *i );
            return a;
        }

    private:
        struct adapter_converter;
        friend struct adapter_converter;

        struct adapter_converter
        {
            const converter& gl;
            adapter_converter( const converter& this_ ) : gl( this_ )
            {}

<<<<<<< HEAD
            adapter_converter( const adapter_converter& r ) 
=======
            adapter_converter( const adapter_converter& r )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            : gl( r.gl )
            { }

            template< class Adapter >
            operator Adapter() const
            {
                return gl.convert_to_adapter<Adapter>();
            }
        };

<<<<<<< HEAD
    public: 
        template< class Container >
        Container to_container( Container& c ) const
        {
            return convert( &c, default_type_tag() ); 
=======
    public:
        template< class Container >
        Container to_container( Container& c ) const
        {
            return convert( &c, default_type_tag() );
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        adapter_converter to_adapter() const
        {
            return adapter_converter( *this );
        }

        template< class Adapter >
        Adapter to_adapter( Adapter& a ) const
        {
<<<<<<< HEAD
            return this->convert_to_adapter( &a ); 
=======
            return this->convert_to_adapter( &a );
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        template< class Array >
        Array to_array( Array& a ) const
        {
            return convert( &a, array_type_tag() );
        }
    };

    template< class T, class I, class Range >
    inline bool operator==( const converter<T,I>& l, const Range& r )
    {
        return ::boost::iterator_range_detail::equal( l, r );
    }

    template< class T, class I, class Range >
    inline bool operator==( const Range& l, const converter<T,I>& r )
    {
        return r == l;
    }

    template< class T, class I, class Range >
    inline bool operator!=( const converter<T,I>& l, const Range& r )
    {
        return !( l == r );
    }
<<<<<<< HEAD
    
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template< class T, class I, class Range >
    inline bool operator!=( const Range& l, const converter<T,I>& r )
    {
        return !( l == r );
    }

    template< class T, class I, class Range >
    inline bool operator<( const converter<T,I>& l, const Range& r )
    {
        return ::boost::iterator_range_detail::less_than( l, r );
    }

    template< class T, class I, class Range >
    inline bool operator<( const Range& l, const converter<T,I>& r )
    {
        return ::boost::iterator_range_detail::less_than( l, r );
    }

    template< class T, class I, class Range >
    inline bool operator>( const converter<T,I>& l, const Range& r )
    {
        return r < l;
    }

    template< class T, class I, class Range >
    inline bool operator>( const Range& l, const converter<T,I>& r )
    {
        return r < l;
    }

    template< class T, class I, class Range >
    inline bool operator<=( const converter<T,I>& l, const Range& r )
    {
        return !( l > r );
    }

    template< class T, class I, class Range >
    inline bool operator<=( const Range& l, const converter<T,I>& r )
    {
        return !( l > r );
    }
<<<<<<< HEAD
    
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template< class T, class I, class Range >
    inline bool operator>=( const converter<T,I>& l, const Range& r )
    {
        return !( l < r );
    }

    template< class T, class I, class Range >
    inline bool operator>=( const Range& l, const converter<T,I>& r )
    {
        return !( l < r );
    }

    template< class T, class I, class Elem, class Traits >
<<<<<<< HEAD
    inline std::basic_ostream<Elem,Traits>& 
=======
    inline std::basic_ostream<Elem,Traits>&
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    operator<<( std::basic_ostream<Elem, Traits>& Os,
                const converter<T,I>& r )
    {
        return Os << ::boost::make_iterator_range( r.begin(), r.end() );
    }
<<<<<<< HEAD
    
    /////////////////////////////////////////////////////////////////////////
    // Part 1: flexible, but inefficient interface
    /////////////////////////////////////////////////////////////////////////    

    template< class T > 
    class generic_list : 
        public converter< generic_list< BOOST_DEDUCED_TYPENAME assign_decay<T>::type >,
                          BOOST_DEDUCED_TYPENAME std::deque<BOOST_DEDUCED_TYPENAME 
=======

    /////////////////////////////////////////////////////////////////////////
    // Part 1: flexible, but inefficient interface
    /////////////////////////////////////////////////////////////////////////

    template< class T >
    class generic_list :
        public converter< generic_list< BOOST_DEDUCED_TYPENAME assign_decay<T>::type >,
                          BOOST_DEDUCED_TYPENAME std::deque<BOOST_DEDUCED_TYPENAME
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                                                            assign_decay<T>::type>::iterator >
    {
        typedef BOOST_DEDUCED_TYPENAME assign_decay<T>::type Ty;
        typedef std::deque<Ty>  impl_type;
        mutable impl_type       values_;
<<<<<<< HEAD
        
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    public:
        typedef BOOST_DEDUCED_TYPENAME impl_type::iterator         iterator;
        typedef iterator                                           const_iterator;
        typedef BOOST_DEDUCED_TYPENAME impl_type::value_type       value_type;
        typedef BOOST_DEDUCED_TYPENAME impl_type::size_type        size_type;
        typedef BOOST_DEDUCED_TYPENAME impl_type::difference_type  difference_type;
<<<<<<< HEAD
        
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    public:
        iterator begin() const       { return values_.begin(); }
        iterator end() const         { return values_.end(); }
        bool empty() const           { return values_.empty(); }
        size_type size() const       { return values_.size(); }
<<<<<<< HEAD
        
    private:
        void push_back( value_type r ) { values_.push_back( r ); }
        
    public:
        generic_list& operator,( const Ty& u )
        {
            this->push_back( u ); 
            return *this;
        }

        generic_list& operator()()
        {
            this->push_back( Ty() );
            return *this;
        }

=======

    private:
#if defined(BOOST_NO_CXX11_RVALUE_REFERENCES)
        void push_back( value_type r ) { values_.push_back( r ); }
#else
        void push_back( const value_type& r ) { values_.push_back( r ); }
        void push_back( value_type&& r ) { values_.push_back( boost::move( r ) ); }
#endif
    public:
        generic_list& operator,( const Ty& u )
        {
            this->push_back( u );
            return *this;
        }

#ifndef BOOST_NO_CXX11_RVALUE_REFERENCES

        generic_list& operator,( Ty&& u )
        {
            this->push_back( boost::move(u) );
            return *this;
        }
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        generic_list& operator()( const Ty& u )
        {
            this->push_back( u );
            return *this;
        }
<<<<<<< HEAD
        
       
#ifndef BOOST_ASSIGN_MAX_PARAMS // use user's value
#define BOOST_ASSIGN_MAX_PARAMS 5
#endif        
#define BOOST_ASSIGN_MAX_PARAMETERS (BOOST_ASSIGN_MAX_PARAMS - 1) 
=======

#ifndef BOOST_NO_CXX11_RVALUE_REFERENCES

        generic_list& operator()(Ty&& u)
        {
            this->push_back( boost::move(u) );
            return *this;
        }
#endif

        generic_list& operator()()
        {
            this->push_back( Ty() );
            return *this;
        }

#if !defined(BOOST_ASSIGN_USE_VARIADIC_TEMPLATES)

#ifndef BOOST_ASSIGN_MAX_PARAMS // use user's value
#define BOOST_ASSIGN_MAX_PARAMS 5
#endif
#define BOOST_ASSIGN_MAX_PARAMETERS (BOOST_ASSIGN_MAX_PARAMS - 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#define BOOST_ASSIGN_PARAMS1(n) BOOST_PP_ENUM_PARAMS(n, class U)
#define BOOST_ASSIGN_PARAMS2(n) BOOST_PP_ENUM_BINARY_PARAMS(n, U, const& u)
#define BOOST_ASSIGN_PARAMS3(n) BOOST_PP_ENUM_PARAMS(n, u)
#define BOOST_ASSIGN_PARAMS4(n) BOOST_PP_ENUM_PARAMS(n, U)
#define BOOST_ASSIGN_PARAMS2_NO_REF(n) BOOST_PP_ENUM_BINARY_PARAMS(n, U, u)

#define BOOST_PP_LOCAL_LIMITS (1, BOOST_ASSIGN_MAX_PARAMETERS)
#define BOOST_PP_LOCAL_MACRO(n) \
    template< class U, BOOST_ASSIGN_PARAMS1(n) > \
    generic_list& operator()(U const& u, BOOST_ASSIGN_PARAMS2(n) ) \
    { \
        this->push_back( Ty(u, BOOST_ASSIGN_PARAMS3(n))); \
        return *this; \
    } \
    /**/
<<<<<<< HEAD
        
#include BOOST_PP_LOCAL_ITERATE()

        
=======

#include BOOST_PP_LOCAL_ITERATE()

#else
        template< class U0, class U1, class... Us >
        generic_list& operator()(U0&& u0, U1&& u1, Us&&... us)
        {
            this->push_back(Ty(boost::forward<U0>(u0), boost::forward<U1>(u1), boost::forward<Us>(us)...));
            return *this;
        }
#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template< class U >
        generic_list& repeat( std::size_t sz, U u )
        {
            std::size_t i = 0;
            while( i++ != sz )
                this->push_back( u );
            return *this;
        }
<<<<<<< HEAD
        
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template< class Nullary_function >
        generic_list& repeat_fun( std::size_t sz, Nullary_function fun )
        {
            std::size_t i = 0;
            while( i++ != sz )
                this->push_back( fun() );
            return *this;
        }

        template< class SinglePassIterator >
<<<<<<< HEAD
        generic_list& range( SinglePassIterator first, 
=======
        generic_list& range( SinglePassIterator first,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                             SinglePassIterator last )
        {
            for( ; first != last; ++first )
                this->push_back( *first );
            return *this;
        }
<<<<<<< HEAD
        
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template< class SinglePassRange >
        generic_list& range( const SinglePassRange& r )
        {
            return range( boost::begin(r), boost::end(r) );
        }
<<<<<<< HEAD

=======
#if !defined(BOOST_NO_CXX11_DECLTYPE_N3276) && !defined(BOOST_NO_CXX11_FUNCTION_TEMPLATE_DEFAULT_ARGS)
        template< class Container,
            class = decltype(Container(
                boost::declval<BOOST_DEDUCED_TYPENAME std::deque<BOOST_DEDUCED_TYPENAME assign_decay<T>::type>::iterator>(),
                boost::declval<BOOST_DEDUCED_TYPENAME std::deque<BOOST_DEDUCED_TYPENAME assign_decay<T>::type>::iterator>()
                ))
        >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }

        template< class Container,
            class = typename boost::enable_if< boost::is_same< boost::type_traits::yes_type, decltype(assign_is_array((Container*)0))> >::type,
            class = void
        >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }
#elif !defined(BOOST_NO_CXX11_FUNCTION_TEMPLATE_DEFAULT_ARGS)
        template< class Container
# if !defined(BOOST_NO_CXX11_HDR_INITIALIZER_LIST)
          , class = typename boost::disable_if< is_initializer_list<Container> >::type
# endif
          , class = typename Container::iterator
        >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }
#else
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template< class Container >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }
<<<<<<< HEAD
    };
    
=======
#endif
    };

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    /////////////////////////////////////////////////////////////////////////
    // Part 2: efficient, but inconvenient interface
    /////////////////////////////////////////////////////////////////////////

    template< class T >
    struct assign_reference
    {
<<<<<<< HEAD
        assign_reference()
=======
        assign_reference() : ref_(0)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        { /* intentionally empty */ }

        assign_reference( T& r ) : ref_(&r)
        { }

        void operator=( T& r )
        {
            ref_ = &r;
        }

        operator T&() const
        {
            return *ref_;
        }

        void swap( assign_reference& r )
        {
            std::swap( *ref_, *r.ref_ );
        }

        T& get_ref() const
        {
            return *ref_;
        }
<<<<<<< HEAD
        
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    private:
        T* ref_;

    };

    template< class T >
<<<<<<< HEAD
    inline bool operator<( const assign_reference<T>& l, 
=======
    inline bool operator<( const assign_reference<T>& l,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                           const assign_reference<T>& r )
    {
        return l.get_ref() < r.get_ref();
    }

    template< class T >
    inline bool operator>( const assign_reference<T>& l,
                           const assign_reference<T>& r )
    {
        return l.get_ref() > r.get_ref();
    }

    template< class T >
<<<<<<< HEAD
    inline void swap( assign_reference<T>& l, 
=======
    inline void swap( assign_reference<T>& l,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                      assign_reference<T>& r )
    {
        l.swap( r );
    }


<<<<<<< HEAD
    
    template< class T, int N >
    struct static_generic_list : 
=======

    template< class T, int N >
    struct static_generic_list :
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        public converter< static_generic_list<T,N>, assign_reference<T>* >
    {
    private:
        typedef T                                     internal_value_type;

    public:
        typedef assign_reference<internal_value_type> value_type;
        typedef value_type*                           iterator;
        typedef value_type*                           const_iterator;
        typedef std::size_t                           size_type;
        typedef std::ptrdiff_t                        difference_type;

<<<<<<< HEAD
    
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        static_generic_list( T& r ) :
            current_(1)
        {
            refs_[0] = r;
        }

        static_generic_list& operator()( T& r )
        {
            insert( r );
            return *this;
        }

<<<<<<< HEAD
        iterator begin() const 
=======
        iterator begin() const
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        {
            return &refs_[0];
        }

        iterator end() const
        {
            return &refs_[current_];
        }

        size_type size() const
        {
<<<<<<< HEAD
            return static_cast<size_type>( current_ ); 
=======
            return static_cast<size_type>( current_ );
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

        bool empty() const
        {
            return false;
        }

        template< class ForwardIterator >
<<<<<<< HEAD
        static_generic_list& range( ForwardIterator first, 
=======
        static_generic_list& range( ForwardIterator first,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                                    ForwardIterator last )
        {
            for( ; first != last; ++first )
                this->insert( *first );
            return *this;
        }

        template< class ForwardRange >
        static_generic_list& range( ForwardRange& r )
        {
            return range( boost::begin(r), boost::end(r) );
        }

        template< class ForwardRange >
        static_generic_list& range( const ForwardRange& r )
        {
            return range( boost::begin(r), boost::end(r) );
        }

<<<<<<< HEAD
=======
#if !defined(BOOST_NO_CXX11_DECLTYPE_N3276) && !defined(BOOST_NO_CXX11_FUNCTION_TEMPLATE_DEFAULT_ARGS)
        template< class Container,
            class = decltype(Container(boost::declval<assign_reference<T>*>(), boost::declval<assign_reference<T>*>()))
        >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }

        template< class Container,
            class = typename boost::enable_if< boost::is_same< boost::type_traits::yes_type, decltype(assign_is_array((Container*)0))> >::type,
            class = void
        >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }
#elif !defined(BOOST_NO_CXX11_FUNCTION_TEMPLATE_DEFAULT_ARGS)
        template< class Container
# if !defined(BOOST_NO_CXX11_HDR_INITIALIZER_LIST)
          , class = typename boost::disable_if< is_initializer_list<Container> >::type
# endif
          , class = typename Container::iterator
        >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }
#else
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        template< class Container >
        operator Container() const
        {
            return this-> BOOST_NESTED_TEMPLATE convert_to_container<Container>();
        }
<<<<<<< HEAD
=======
#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

    private:
        void insert( T& r )
        {
            refs_[current_] = r;
            ++current_;
        }
<<<<<<< HEAD
        
        static_generic_list();
        
=======

        static_generic_list();

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        mutable assign_reference<internal_value_type> refs_[N];
        int current_;
    };

} // namespace 'assign_detail'

namespace assign
{
    template< class T >
<<<<<<< HEAD
    inline assign_detail::generic_list<T>
    list_of()
    {
        return assign_detail::generic_list<T>()( T() );
    }
    
    template< class T >
    inline assign_detail::generic_list<T> 
=======
    inline assign_detail::generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type>
    list_of()
    {
        assign_detail::generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type> gl;
        gl();
        return gl;
    }

#if !defined(BOOST_ASSIGN_USE_VARIADIC_TEMPLATES)

    template< class T >
    inline assign_detail::generic_list<T>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    list_of( const T& t )
    {
        return assign_detail::generic_list<T>()( t );
    }

<<<<<<< HEAD
=======
#else

    template< class T >
    inline assign_detail::generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type>
    list_of(T&& t)
    {
        assign_detail::generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type> gl;
        gl(boost::forward<T>(t));
        return gl;
    }

#endif

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template< int N, class T >
    inline assign_detail::static_generic_list< BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type,N>
    ref_list_of( T& t )
    {
        return assign_detail::static_generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type,N>( t );
    }
<<<<<<< HEAD
    
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template< int N, class T >
    inline assign_detail::static_generic_list<const BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type,N>
    cref_list_of( const T& t )
    {
        return assign_detail::static_generic_list<const BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type,N>( t );
    }

<<<<<<< HEAD
=======
#if !defined(BOOST_ASSIGN_USE_VARIADIC_TEMPLATES)

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#define BOOST_PP_LOCAL_LIMITS (1, BOOST_ASSIGN_MAX_PARAMETERS)
#define BOOST_PP_LOCAL_MACRO(n) \
    template< class T, class U, BOOST_ASSIGN_PARAMS1(n) > \
    inline assign_detail::generic_list<T> \
    list_of(U const& u, BOOST_ASSIGN_PARAMS2(n) ) \
    { \
        return assign_detail::generic_list<T>()(u, BOOST_ASSIGN_PARAMS3(n)); \
    } \
    /**/
<<<<<<< HEAD
    
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include BOOST_PP_LOCAL_ITERATE()

#define BOOST_PP_LOCAL_LIMITS (1, BOOST_ASSIGN_MAX_PARAMETERS)
#define BOOST_PP_LOCAL_MACRO(n) \
    template< class U, BOOST_ASSIGN_PARAMS1(n) > \
    inline assign_detail::generic_list< tuple<U, BOOST_ASSIGN_PARAMS4(n)> > \
    tuple_list_of(U u, BOOST_ASSIGN_PARAMS2_NO_REF(n) ) \
    { \
        return assign_detail::generic_list< tuple<U, BOOST_ASSIGN_PARAMS4(n)> >()( tuple<U,BOOST_ASSIGN_PARAMS4(n)>( u, BOOST_ASSIGN_PARAMS3(n) )); \
    } \
    /**/
<<<<<<< HEAD
    
#include BOOST_PP_LOCAL_ITERATE()


    template< class Key, class T >
    inline assign_detail::generic_list< std::pair
        < 
            BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<Key>::type, 
=======

#include BOOST_PP_LOCAL_ITERATE()

#else
    template< class T, class U, class... Us >
    inline assign_detail::generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type>
    list_of(U&& u, Us&&... us)
    {
        assign_detail::generic_list<BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type> gl;
        gl(boost::forward<U>(u), boost::forward<Us>(us)...);
        return gl;
    }


    template< class U, class... Us >
    inline assign_detail::generic_list< tuple<U, Us...> >
    tuple_list_of(U u, Us... us)
    {
        assign_detail::generic_list< tuple<U, Us...> > gl;
        gl(tuple<U, Us...>(u, us...));
        return gl;
    }
#endif

    template< class Key, class T >
    inline assign_detail::generic_list< std::pair
        <
            BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<Key>::type,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type
        > >
    map_list_of( const Key& k, const T& t )
    {
        typedef BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<Key>::type k_type;
        typedef BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<T>::type   t_type;
        return assign_detail::generic_list< std::pair<k_type,t_type> >()( k, t );
    }

    template< class F, class S >
    inline assign_detail::generic_list< std::pair
<<<<<<< HEAD
        < 
            BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<F>::type, 
=======
        <
            BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<F>::type,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            BOOST_DEDUCED_TYPENAME assign_detail::assign_decay<S>::type
        > >
    pair_list_of( const F& f, const S& s )
    {
        return map_list_of( f, s );
    }


} // namespace 'assign'
} // namespace 'boost'


<<<<<<< HEAD
=======
#if !defined(BOOST_ASSIGN_USE_VARIADIC_TEMPLATES)

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#undef BOOST_ASSIGN_PARAMS1
#undef BOOST_ASSIGN_PARAMS2
#undef BOOST_ASSIGN_PARAMS3
#undef BOOST_ASSIGN_PARAMS4
#undef BOOST_ASSIGN_PARAMS2_NO_REF
#undef BOOST_ASSIGN_MAX_PARAMETERS

#endif
<<<<<<< HEAD
=======


#endif
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
